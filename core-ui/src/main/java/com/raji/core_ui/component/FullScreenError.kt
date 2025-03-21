package com.raji.core_ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raji.core_ui.R

@Composable
fun FullScreenError(errorMsg: String, @DrawableRes errorIcon: Int=R.drawable.alert_error_icon) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Icon(
            painter = painterResource(errorIcon),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = errorMsg,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
fun FullScreenErrorPreview() {
    FullScreenError(
        errorMsg = "Something went wrong",
        errorIcon = android.R.drawable.ic_dialog_alert
    )
}