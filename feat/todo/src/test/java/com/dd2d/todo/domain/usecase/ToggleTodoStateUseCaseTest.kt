@file:Suppress("MISSING_DEPENDENCY_IN_INFERRED_TYPE_ANNOTATION_WARNING")

package com.dd2d.todo.domain.usecase

import com.dd2d.todo.domain.model.Todo
import com.dd2d.todo.domain.model.TodoUpdateData
import com.dd2d.todo.domain.repository.TodoRepository
import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.ZonedDateTime
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.uuid.Uuid

/**
 * [ToggleTodoStateUseCase]의 상태 토글 및 연쇄 업데이트 로직을 검증하는 테스트 클래스입니다.
 * 상세 테스트 케이스 정의 및 구현 가이드는 아래 문서를 참조하세요.
 *
 * [노션 상세 가이드](https://www.notion.so/3163235ed4f880b69d71ee1f24edba22)
 */
class ToggleTodoStateUseCaseTest {
  private val todoRepository: TodoRepository = mockk(relaxed = true)
  private val toggleTodoStateUseCase: ToggleTodoStateUseCase = ToggleTodoStateUseCase(todoRepository)
  private val fm = FixtureMonkey.builder()
    .plugin(KotlinPlugin())
    .build()

  @Test
  fun `TC_TODO_TOGGLE_001 - 미완료 Todo 완료 처리`() = runTest {
    // Given
    val targetId = Uuid.random()
    val targetTodo = fm.giveMeBuilder<Todo>()
      .set("id", targetId)
      .set("completedAt", null)
      .set("subTodos", emptyList<Todo>())
      .set("parentId", null)
      .sample()

    val updatedTodo = targetTodo.copy(completedAt = ZonedDateTime.now())
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(targetTodo, updatedTodo)

    // When
    val result = toggleTodoStateUseCase(targetId)

    // Then
    coVerify(exactly = 1) {
      todoRepository.updateTodo(targetId, match { it.completedAt != null })
    }
    coVerify(exactly = 0) {
      todoRepository.updateTodos(any(), any())
    }
    assertTrue(
      actual = result.isComplete,
      message = "미완료 상태의 Todo를 토글하면 완료 상태가 되어야 함"
    )
    assertNotNull(
      actual = result.completedAt,
      message = "완료된 Todo의 completedAt은 null이 아니어야 함"
    )
  }

  @Test
  fun `TC_TODO_TOGGLE_002 - 완료 Todo 미완료 처리`() = runTest {
    // Given
    val targetId = Uuid.random()
    val targetTodo = fm.giveMeBuilder<Todo>()
      .set("id", targetId)
      .set("completedAt", ZonedDateTime.now())
      .set("subTodos", emptyList<Todo>())
      .set("parentId", null)
      .sample()

    val updatedTodo = targetTodo.copy(completedAt = null)
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(targetTodo, updatedTodo)

    // When
    val result = toggleTodoStateUseCase(targetId)

    // Then
    coVerify(exactly = 1) {
      todoRepository.updateTodo(targetId, match { it.completedAt == null })
    }
    assertEquals(
      expected = false,
      actual = result.isComplete,
      message = "완료 상태의 Todo를 토글하면 미완료 상태가 되어야 함"
    )
    assertNull(
      actual = result.completedAt,
      message = "미완료된 Todo의 completedAt은 null이어야 함"
    )
  }

  @Test
  fun `TC_TODO_TOGGLE_003 - 부모 완료 시 모든 하위 항목 반복적 완료`() = runTest {
    // Given
    val grandchildId = Uuid.random()
    val childId = Uuid.random()
    val parentId = Uuid.random()

    val grandchild = fm.giveMeBuilder<Todo>()
      .set("id", grandchildId)
      .set("completedAt", null)
      .set("subTodos", emptyList<Todo>())
      .sample()

    val child = fm.giveMeBuilder<Todo>()
      .set("id", childId)
      .set("completedAt", null)
      .set("subTodos", listOf(grandchild))
      .sample()

    val parent = fm.giveMeBuilder<Todo>()
      .set("id", parentId)
      .set("completedAt", null)
      .set("subTodos", listOf(child))
      .set("parentId", null)
      .sample()

    val updatedParent = parent.copy(completedAt = ZonedDateTime.now())
    coEvery { todoRepository.getTodo(parentId) } returnsMany listOf(parent, updatedParent)

    // When
    val result = toggleTodoStateUseCase(parentId)

    // Then
    coVerify {
      todoRepository.updateTodos(
        match { it.containsAll(listOf(childId, grandchildId)) },
        match { it.completedAt != null }
      )
    }
    assertTrue(
      actual = result.isComplete,
      message = "부모 Todo 토글 결과는 완료 상태여야 함"
    )
  }

  @Test
  fun `TC_TODO_TOGGLE_004 - 부모 미완료 시 모든 하위 항목 반복적 미완료`() = runTest {
    // Given
    val childId = Uuid.random()
    val parentId = Uuid.random()

    val child = fm.giveMeBuilder<Todo>()
      .set("id", childId)
      .set("completedAt", ZonedDateTime.now())
      .set("subTodos", emptyList<Todo>())
      .sample()

    val parent = fm.giveMeBuilder<Todo>()
      .set("id", parentId)
      .set("completedAt", ZonedDateTime.now())
      .set("subTodos", listOf(child))
      .set("parentId", null)
      .sample()

    val updatedParent = parent.copy(completedAt = null)
    coEvery { todoRepository.getTodo(parentId) } returnsMany listOf(parent, updatedParent)

    // When
    val result = toggleTodoStateUseCase(parentId)

    // Then
    coVerify {
      todoRepository.updateTodos(
        match { it.contains(childId) },
        match { it.completedAt == null }
      )
    }
    assertEquals(
      expected = false,
      actual = result.isComplete,
      message = "부모 Todo 토글 결과는 미완료 상태여야 함"
    )
  }

  @Test
  fun `TC_TODO_TOGGLE_005 - 마지막 미완료 형제 완료 시 부모 자동 완료`() = runTest {
    // Given
    val parentId = Uuid.random()
    val targetId = Uuid.random()
    val siblingId = Uuid.random()

    val target = fm.giveMeBuilder<Todo>()
      .set("id", targetId)
      .set("parentId", parentId)
      .set("completedAt", null)
      .set("subTodos", emptyList<Todo>())
      .sample()

    val sibling = fm.giveMeBuilder<Todo>()
      .set("id", siblingId)
      .set("parentId", parentId)
      .set("completedAt", ZonedDateTime.now())
      .set("subTodos", emptyList<Todo>())
      .sample()

    val parent = fm.giveMeBuilder<Todo>()
      .set("id", parentId)
      .set("completedAt", null)
      .set("parentId", null)
      .set("subTodos", listOf(target, sibling))
      .sample()

    val updatedParent = parent.copy(completedAt = ZonedDateTime.now())
    val updatedTarget = target.copy(completedAt = ZonedDateTime.now())
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(target, updatedTarget)
    coEvery { todoRepository.getTodo(parentId) } returnsMany listOf(parent, updatedParent)
    coEvery { todoRepository.getSubTodos(parentId) } returns listOf(
      target.copy(completedAt = ZonedDateTime.now()),
      sibling
    )

    // When
    val result = toggleTodoStateUseCase(targetId)

    // Then
    coVerify(exactly = 1) {
      todoRepository.updateTodo(parentId, match { it.completedAt != null })
    }
    assertTrue(
      actual = result.isComplete,
      message = "타겟 Todo는 완료 상태여야 함"
    )
  }

  @Test
  fun `TC_TODO_TOGGLE_006 - 완료된 형제 중 하나 미완료 시 부모 자동 미완료`() = runTest {
    // Given
    val parentId = Uuid.random()
    val targetId = Uuid.random()
    val siblingId = Uuid.random()

    val target = fm.giveMeBuilder<Todo>()
      .set("id", targetId)
      .set("parentId", parentId)
      .set("completedAt", ZonedDateTime.now())
      .set("subTodos", emptyList<Todo>())
      .sample()

    val sibling = fm.giveMeBuilder<Todo>()
      .set("id", siblingId)
      .set("parentId", parentId)
      .set("completedAt", ZonedDateTime.now())
      .set("subTodos", emptyList<Todo>())
      .sample()

    val parent = fm.giveMeBuilder<Todo>()
      .set("id", parentId)
      .set("completedAt", ZonedDateTime.now())
      .set("parentId", null)
      .set("subTodos", listOf(target, sibling))
      .sample()

    val updatedParent = parent.copy(completedAt = null)
    val updatedTarget = target.copy(completedAt = null)
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(target, updatedTarget)
    coEvery { todoRepository.getTodo(parentId) } returnsMany listOf(parent, updatedParent)
    coEvery { todoRepository.getSubTodos(parentId) } returns listOf(
      target.copy(completedAt = null),
      sibling
    )

    // When
    val result = toggleTodoStateUseCase(targetId)

    // Then
    coVerify(exactly = 1) {
      todoRepository.updateTodo(parentId, match { it.completedAt == null })
    }
    assertEquals(
      expected = false,
      actual = result.isComplete,
      message = "타겟 Todo는 미완료 상태여야 함"
    )
  }

  @Test
  fun `TC_TODO_TOGGLE_007 - 최하위 자식 완료로 인한 최상위 부모까지의 연쇄 완료`() = runTest {
    // Given
    val rootId = Uuid.random()
    val childId = Uuid.random()
    val targetId = Uuid.random()

    val target = fm.giveMeBuilder<Todo>()
      .set("id", targetId)
      .set("parentId", childId)
      .set("completedAt", null)
      .set("subTodos", emptyList<Todo>())
      .sample()

    val child = fm.giveMeBuilder<Todo>()
      .set("id", childId)
      .set("parentId", rootId)
      .set("completedAt", null)
      .set("subTodos", listOf(target))
      .sample()

    val root = fm.giveMeBuilder<Todo>()
      .set("id", rootId)
      .set("parentId", null)
      .set("completedAt", null)
      .set("subTodos", listOf(child))
      .sample()

    val updatedTarget = target.copy(completedAt = ZonedDateTime.now())
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(target, updatedTarget)
    coEvery { todoRepository.getTodo(childId) } returns child
    coEvery { todoRepository.getTodo(rootId) } returns root

    coEvery { todoRepository.getSubTodos(childId) } returns listOf(target.copy(completedAt = ZonedDateTime.now()))
    coEvery { todoRepository.getSubTodos(rootId) } returns listOf(child.copy(completedAt = ZonedDateTime.now()))

    // When
    val result = toggleTodoStateUseCase(targetId)

    // Then
    coVerify(exactly = 1) { todoRepository.updateTodo(childId, match { it.completedAt != null }) }
    coVerify(exactly = 1) { todoRepository.updateTodo(rootId, match { it.completedAt != null }) }
    assertTrue(
      actual = result.isComplete,
      message = "연쇄 업데이트 결과로 타겟 Todo도 완료 상태여야 함"
    )
  }

  @Test(expected = NoSuchElementException::class)
  fun `TC_TODO_TOGGLE_008 - 존재하지 않는 Todo ID 요청`() = runTest {
    // Given
    val invalidId = Uuid.random()
    coEvery { todoRepository.getTodo(invalidId) } throws NoSuchElementException()

    // When
    toggleTodoStateUseCase(invalidId)

    // Then - Exception expected
  }

  @Test
  fun `TC_TODO_TOGGLE_009 - 이미 부모가 목표 상태인 경우 중복 업데이트 방지`() = runTest {
    // Given
    val parentId = Uuid.random()
    val targetId = Uuid.random()

    val target = fm.giveMeBuilder<Todo>()
      .set("id", targetId)
      .set("parentId", parentId)
      .set("completedAt", null)
      .set("subTodos", emptyList<Todo>())
      .sample()

    val parent = fm.giveMeBuilder<Todo>()
      .set("id", parentId)
      .set("completedAt", ZonedDateTime.now()) // 이미 완료 상태
      .set("parentId", null)
      .set("subTodos", listOf(target))
      .sample()

    val updatedTarget = target.copy(completedAt = ZonedDateTime.now())
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(target, updatedTarget)
    coEvery { todoRepository.getTodo(parentId) } returns parent
    coEvery { todoRepository.getSubTodos(parentId) } returns listOf(target.copy(completedAt = ZonedDateTime.now()))

    // When
    val result = toggleTodoStateUseCase(targetId)

    // Then
    // 부모가 이미 완료 상태이므로 updateTodo(parentId, ...)는 호출되지 않아야 함
    coVerify(exactly = 0) { todoRepository.updateTodo(parentId, any()) }
    assertTrue(
      actual = result.isComplete,
      message = "부모가 이미 목표 상태여도 타겟은 완료 상태가 되어야 함"
    )
  }
}