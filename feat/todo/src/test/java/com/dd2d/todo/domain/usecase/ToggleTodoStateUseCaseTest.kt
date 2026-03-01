package com.dd2d.todo.domain.usecase

import com.dd2d.todo.domain.model.Todo
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
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(
      Result.success(targetTodo),
      Result.success(updatedTodo)
    )
    coEvery { todoRepository.updateTodo(any(), any()) } returns Result.success(Unit)

    // When
    val result = toggleTodoStateUseCase(targetId).getOrThrow()

    // Then
    coVerify(exactly = 1) {
      todoRepository.updateTodo(targetId, match { it.completedAt != null })
    }
    assertTrue(result.isComplete)
    assertNotNull(result.completedAt)
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
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(
      Result.success(targetTodo),
      Result.success(updatedTodo)
    )
    coEvery { todoRepository.updateTodo(any(), any()) } returns Result.success(Unit)

    // When
    val result = toggleTodoStateUseCase(targetId).getOrThrow()

    // Then
    coVerify(exactly = 1) {
      todoRepository.updateTodo(targetId, match { it.completedAt == null })
    }
    assertEquals(false, result.isComplete)
    assertNull(result.completedAt)
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
    coEvery { todoRepository.getTodo(parentId) } returnsMany listOf(
      Result.success(parent),
      Result.success(updatedParent)
    )
    coEvery { todoRepository.updateTodo(any(), any()) } returns Result.success(Unit)
    coEvery { todoRepository.updateTodos(any(), any()) } returns Result.success(Unit)

    // When
    val result = toggleTodoStateUseCase(parentId).getOrThrow()

    // Then
    coVerify {
      todoRepository.updateTodos(
        match { it.containsAll(listOf(childId, grandchildId)) },
        match { it.completedAt != null }
      )
    }
    assertTrue(result.isComplete)
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
    coEvery { todoRepository.getTodo(parentId) } returnsMany listOf(
      Result.success(parent),
      Result.success(updatedParent)
    )
    coEvery { todoRepository.updateTodo(any(), any()) } returns Result.success(Unit)
    coEvery { todoRepository.updateTodos(any(), any()) } returns Result.success(Unit)

    // When
    val result = toggleTodoStateUseCase(parentId).getOrThrow()

    // Then
    coVerify {
      todoRepository.updateTodos(
        match { it.contains(childId) },
        match { it.completedAt == null }
      )
    }
    assertEquals(false, result.isComplete)
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
    
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(
      Result.success(target),
      Result.success(updatedTarget)
    )
    coEvery { todoRepository.getTodo(parentId) } returnsMany listOf(
      Result.success(parent),
      Result.success(updatedParent)
    )
    coEvery { todoRepository.getSubTodos(parentId) } returns Result.success(listOf(
      target.copy(completedAt = ZonedDateTime.now()),
      sibling
    ))
    coEvery { todoRepository.updateTodo(any(), any()) } returns Result.success(Unit)

    // When
    val result = toggleTodoStateUseCase(targetId).getOrThrow()

    // Then
    coVerify(exactly = 1) {
      todoRepository.updateTodo(parentId, match { it.completedAt != null })
    }
    assertTrue(result.isComplete)
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
    
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(
      Result.success(target),
      Result.success(updatedTarget)
    )
    coEvery { todoRepository.getTodo(parentId) } returnsMany listOf(
      Result.success(parent),
      Result.success(updatedParent)
    )
    coEvery { todoRepository.getSubTodos(parentId) } returns Result.success(listOf(
      target.copy(completedAt = null),
      sibling
    ))
    coEvery { todoRepository.updateTodo(any(), any()) } returns Result.success(Unit)

    // When
    val result = toggleTodoStateUseCase(targetId).getOrThrow()

    // Then
    coVerify(exactly = 1) {
      todoRepository.updateTodo(parentId, match { it.completedAt == null })
    }
    assertEquals(false, result.isComplete)
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
    
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(
      Result.success(target),
      Result.success(updatedTarget)
    )
    coEvery { todoRepository.getTodo(childId) } returns Result.success(child)
    coEvery { todoRepository.getTodo(rootId) } returns Result.success(root)

    coEvery { todoRepository.getSubTodos(childId) } returns Result.success(listOf(target.copy(completedAt = ZonedDateTime.now())))
    coEvery { todoRepository.getSubTodos(rootId) } returns Result.success(listOf(child.copy(completedAt = ZonedDateTime.now())))
    coEvery { todoRepository.updateTodo(any(), any()) } returns Result.success(Unit)

    // When
    val result = toggleTodoStateUseCase(targetId).getOrThrow()

    // Then
    coVerify(exactly = 1) { todoRepository.updateTodo(childId, match { it.completedAt != null }) }
    coVerify(exactly = 1) { todoRepository.updateTodo(rootId, match { it.completedAt != null }) }
    assertTrue(result.isComplete)
  }

  @Test(expected = NoSuchElementException::class)
  fun `TC_TODO_TOGGLE_008 - 존재하지 않는 Todo ID 요청`() = runTest {
    // Given
    val invalidId = Uuid.random()
    coEvery { todoRepository.getTodo(invalidId) } returns Result.failure(NoSuchElementException())

    // When
    toggleTodoStateUseCase(invalidId).getOrThrow()
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
      .set("completedAt", ZonedDateTime.now())
      .set("parentId", null)
      .set("subTodos", listOf(target))
      .sample()

    val updatedTarget = target.copy(completedAt = ZonedDateTime.now())
    
    coEvery { todoRepository.getTodo(targetId) } returnsMany listOf(
      Result.success(target),
      Result.success(updatedTarget)
    )
    coEvery { todoRepository.getTodo(parentId) } returns Result.success(parent)
    coEvery { todoRepository.getSubTodos(parentId) } returns Result.success(listOf(target.copy(completedAt = ZonedDateTime.now())))
    coEvery { todoRepository.updateTodo(any(), any()) } returns Result.success(Unit)

    // When
    val result = toggleTodoStateUseCase(targetId).getOrThrow()

    // Then
    coVerify(exactly = 0) { todoRepository.updateTodo(parentId, any()) }
    assertTrue(result.isComplete)
  }
}
