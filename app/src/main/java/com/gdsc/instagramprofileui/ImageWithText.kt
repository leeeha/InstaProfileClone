package com.gdsc.instagramprofileui

import androidx.compose.ui.graphics.painter.Painter

// 데이터 보관이 주목적인 클래스
data class ImageWithText(
    val image: Painter,
    val text: String,
)
