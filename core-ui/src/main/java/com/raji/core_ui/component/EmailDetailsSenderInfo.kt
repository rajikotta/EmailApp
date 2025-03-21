package com.raji.core_ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Shortcut
import androidx.compose.material.icons.outlined.Mood
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raji.core_ui.R

@Composable
fun EmailDetailsSenderInfo(
    modifier: Modifier = Modifier,
    profileImageUrl: String,
    isPromotional: Boolean,
    from: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProfileImage(
            modifier = Modifier,
            imageSource = profileImageUrl,
            size = 32.dp
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    modifier = Modifier.weight(4f),
                    text = from,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = "7 days ago",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1
                )
            }
            Text(
                text = stringResource(R.string.txt_to_me),
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1
            )
        }
        if (!isPromotional) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(4.dp)
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Mood,
                    contentDescription = "React with emoji",
                    modifier =
                    Modifier
                        .size(24.dp)
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(4.dp)
                    .size(32.dp)
                    .scale(scaleX = -1f, scaleY = 1f)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Shortcut,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        } else {
            TextButton(
                onClick = { }
            ) {
                Text(stringResource(R.string.txt_unsubscribe))
            }
        }
        IconButton(
            onClick = {},
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun PreviewEmailDetailsSenderInfo(
    @PreviewParameter(EmailDetailsSenderInfoPreviewProvider::class)
    emailDetailsSenderInfoPreviewData: EmailDetailsSenderInfoPreviewData
) {
    EmailDetailsSenderInfo(
        profileImageUrl = emailDetailsSenderInfoPreviewData.profileImageUrl,
        isPromotional = emailDetailsSenderInfoPreviewData.isPromotional,
        from = emailDetailsSenderInfoPreviewData.from
    )
}


data class EmailDetailsSenderInfoPreviewData(
    val profileImageUrl: String,
    val isPromotional: Boolean,
    val from: String
)
class EmailDetailsSenderInfoPreviewProvider : androidx.compose.ui.tooling.preview.PreviewParameterProvider<EmailDetailsSenderInfoPreviewData> {
    override val values: Sequence<EmailDetailsSenderInfoPreviewData> = sequenceOf(EmailDetailsSenderInfoPreviewData("https://i.pravatar.cc/150?img=1", false, "Amazon"), EmailDetailsSenderInfoPreviewData("https://i.pravatar.cc/150?img=2", true, "Udemy"))
}