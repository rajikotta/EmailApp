package com.raji.core_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CircularProfileImage(
    modifier: Modifier = Modifier, imageSource: String,
    size: Dp = 32.dp
) {
    GlideImage(
        model = imageSource,
        contentDescription = null,
        modifier =
        modifier
            .size(size)
            .background(
                color = Color.Gray,
                shape = CircleShape
            )
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun PreviewCircularProfileImage() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProfileImage(imageSource = "https://via.placeholder.com/150", size = 64.dp)
        CircularProfileImage(imageSource = "https://via.placeholder.com/150")
    }
}