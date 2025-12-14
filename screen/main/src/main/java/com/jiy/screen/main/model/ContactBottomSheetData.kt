package com.jiy.screen.main.model

import com.jiy.screen.main.component.ContactBottomSheet

/**
 * [ContactBottomSheet]로 보여줄 데이터 모델
 *
 * @property label 데이터의 이름
 * @property value 데이터의 값
 * @property actionLabel 데이터를 이용한 행동의 이름
 * @property onAction 데이터를 이용한 행동
 */
internal data class ContactBottomSheetData(
  val label: String,
  val value: String,
  val actionLabel: String,
  val onAction: () -> Unit,
)