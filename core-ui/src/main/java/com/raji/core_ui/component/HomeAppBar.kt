package com.raji.core_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.raji.core_ui.R

@Composable
fun HomeAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(48.dp),
            ), verticalAlignment = Alignment.CenterVertically

    ) {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
        }
        Text(text = stringResource(R.string.search), modifier = Modifier.weight(1f))
        CircularProfileImage(
            modifier = Modifier.padding(horizontal = 16.dp),
            imageSource = "https://i.pravatar.cc/250?img=5"
        )
    }
}

@Preview
@Composable
private fun HomeAppBarPreview() {
    HomeAppBar() }