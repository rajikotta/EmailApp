package com.raji.core_ui.component

import android.R.attr.onClick
import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.raji.core_ui.content.getDrawerItems

@Composable
fun DetailsAppBar(modifier: Modifier = Modifier, navController: NavController) {


    val density = LocalDensity.current
    var isMenuExpanded by remember { mutableStateOf(false) }
    var offsetX by remember { mutableStateOf(0.dp) }
    var parentWidth by remember { mutableIntStateOf(0) }





    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {}) {
            Icon(
                Icons.Default.ArrowBackIosNew,
                contentDescription = "Back"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Icon(
                Icons.Default.Archive,
                contentDescription = "Archive"
            )
        }
        IconButton(onClick = {}) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
        IconButton(onClick = {}) {
            Icon(
                Icons.Default.Mail,
                contentDescription = "Mail"
            )
        }
        IconButton(onClick = { isMenuExpanded = !isMenuExpanded }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "More Options "
            )
        }
        DropdownMenu(
            modifier = Modifier.onPlaced {
                val menuwidth = parentWidth - it.size.width
                offsetX = with(density) { menuwidth.toDp() }

            },
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false },
            offset = DpOffset(offsetX, 0.dp),
        ) {
            getDrawerItems().forEach { it ->



            }
        }

    }
}

