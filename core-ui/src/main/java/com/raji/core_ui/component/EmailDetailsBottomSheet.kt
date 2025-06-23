package com.raji.core_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material.icons.automirrored.filled.Shortcut
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Preview(showBackground = true)
@Composable
fun EmailDetailsBottomSheet(modifier: Modifier = Modifier, inReplyMode: Boolean = false) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Attachment,
                contentDescription = "Attach",
                modifier = Modifier.size(24.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            if (inReplyMode) {
                ReplyBarWithInput()
            } else {
                ReplyBar()
            }

        }

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Mood,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }

}




@Preview
@Composable
fun ReplyBar() {
    Row(verticalAlignment = Alignment.CenterVertically) {


        IconButton(
            onClick = {}, modifier = Modifier
                .padding(4.dp)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.Reply,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Reply", color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {}, modifier = Modifier
                .padding(4.dp)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.Shortcut,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

    }


}

@Preview
@Composable
fun ReplyBarWithInput() {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Row {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Shortcut,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {}, modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Shortcut,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(
                onClick = {}, modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.OpenInNew,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        TextField(value = "Reply", onValueChange = {}, placeholder = { Text("Compose Email") },
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
    }
}
