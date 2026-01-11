package com.dd2d.json_placeholder._core.data

// json placeholder에서 제공하지 않아 임의로 생성
internal fun getUserNameById(id: Int): String {
  val users = listOf(
    1 to "Bret",
    2 to "Antonette",
    3 to "Samantha",
    4 to "Karianne",
    5 to "Kamren",
    6 to "Leopoldo_Corkery",
    7 to "Elwyn.Skiles",
    8 to "Maxime_Nienow",
    9 to "Delphine",
    10 to "Moriah.Stanton",
  )

  return users.find { it.first == id }?.second ?: "user$id"
}

internal fun getUserProfileImageById(id: Int): String = "https://i.pravatar.cc/150?img=$id"