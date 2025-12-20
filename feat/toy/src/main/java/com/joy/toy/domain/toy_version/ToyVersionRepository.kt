package com.joy.toy.domain.toy_version

import com.joy.toy.domain.toy_core.model.ToyVersion

interface ToyVersionRepository {
  suspend fun getToyVersionList(toyId: Int): List<ToyVersion>
}