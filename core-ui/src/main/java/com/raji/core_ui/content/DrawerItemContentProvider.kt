package com.raji.core_ui.content

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.LabelImportant
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Drafts
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LabelImportant
import androidx.compose.material.icons.filled.Outbox
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Snooze
import androidx.compose.material.icons.filled.StarRate
import com.raji.core_ui.uimodel.DrawerData

fun getDrawerItems() = listOf<DrawerData>(

    DrawerData.Title("Email"),
    DrawerData.Divider,
    DrawerData.Item("All Inbox", Icons.Filled.Inbox),
    DrawerData.Divider,
    DrawerData.Item("Primary", Icons.Filled.Inbox),
    DrawerData.Item("Update", Icons.Filled.Info),
    DrawerData.Item("Forum", Icons.Filled.Forum),
    DrawerData.Category("All Label"),
    DrawerData.Item("Starred", Icons.Filled.StarRate),
    DrawerData.Item("Snoozed", Icons.Filled.Snooze),
    DrawerData.Item("Important", Icons.AutoMirrored.Filled.LabelImportant),
    DrawerData.Item("Send", Icons.AutoMirrored.Filled.Send),
    DrawerData.Item("Schedule", Icons.Filled.Schedule),
    DrawerData.Item("Outbox", Icons.Filled.Outbox),
    DrawerData.Item("Draft", Icons.Filled.Drafts),
    DrawerData.Item("All Email", Icons.Filled.AllInbox),
    DrawerData.Item("Delete", Icons.Filled.Delete),
    DrawerData.Category("Google Apps"),


    DrawerData.Item("Calendar", Icons.Filled.CalendarMonth),
    DrawerData.Item("Contacts", Icons.Filled.Contacts),
    DrawerData.Divider,
    DrawerData.Item("Settings", Icons.Filled.Settings),
    DrawerData.Item("Help", Icons.AutoMirrored.Filled.Help),

    )