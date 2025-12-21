package com.joy.toy.domain.toy_core

import com.joy.toy.domain.toy_core.model.ToyDetail
import com.joy.toy.domain.toy_core.model.ToyListItem
import com.joy.toy.domain.toy_core.model.ToyListOption

interface ToyRepository {
  suspend fun getToyList(option: ToyListOption): List<ToyListItem>
  suspend fun getToyDetail(id: Int): ToyDetail
}