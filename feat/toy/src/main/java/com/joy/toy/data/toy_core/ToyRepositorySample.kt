package com.joy.toy.data.toy_core

import com.joy.toy.domain.toy_core.ToyRepository
import com.joy.toy.domain.toy_core.model.ToyCode
import com.joy.toy.domain.toy_core.model.ToyDetail
import com.joy.toy.domain.toy_core.model.ToyImage
import com.joy.toy.domain.toy_core.model.ToyListItem
import com.joy.toy.domain.toy_core.model.ToyListOption
import com.joy.toy.domain.toy_core.model.ToyVersion
import kotlinx.coroutines.delay
import kotlin.random.Random

internal object ToyRepositorySample: ToyRepository {
  override suspend fun getToyList(option: ToyListOption): List<ToyListItem> {
    delay(300)
    return List(option.size) { index ->
      val resourceId = option.page * option.size + index
      val toyId = Random.nextInt()
      ToyListItem(
        id = resourceId,
        toyId = toyId,
        name = "장난감$toyId-$resourceId",
        description = "장난감 설명",
        thumbnailUrl = "https://image.utoimage.com/preview/cp872722/2022/12/202212008462_500.jpg",
        keywords = List((1..9).random()) { "키워드${it+1}" },
        version = "1.0.0",
      )
    }
  }

  override suspend fun getToy(id: Int): ToyDetail {
    delay(300)
    val toyId = Random.nextInt()
    return ToyDetail(
      id = id,
      toyId = toyId,
      name = "장난감$toyId-$id",
      description = "장난감 설명",
      thumbnailUrl = "https://image.utoimage.com/preview/cp872722/2022/12/202212008462_500.jpg",
      images = List(3) {
        ToyImage(
          id = it+1,
          toyResourceId = id,
          imageUrl = "https://image.utoimage.com/preview/cp872722/2022/12/202212008462_500.jpg",
          description = "장난감 이미지 ${it + 1}",
        )
      },
      keywords = List((1..9).random()) { "키워드${it+1}" },
      codes = List(2) {
        ToyCode(
          id = it+1,
          toyResourceId = id,
          code = """
            fun someCode() {
              println("hello world!")
            }
          """.trimIndent(),
          description = "코드 설명${it+1}",
        )
      },
      version = ToyVersion(
        id = Random.nextInt(),
        toyResourceId = id,
        version = "1.0.0",
        releaseNote = """
          무언가 변경되었습니다.
          ⛳️새로운 기능이 추가됐을까요?
        """.trimIndent(),
        prevVersionId = null,
        nextVersionId = null,
      ),
      gitHubLink = "https://github.com/example/toy",
    )
  }
}