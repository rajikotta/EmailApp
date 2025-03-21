package com.raji.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raji.core_ui.component.EmailItem
import com.raji.core_ui.component.FullScreenError
import com.raji.core_ui.component.LinearFullScreenProgress
import com.raji.core_ui.functional.toformattedDate
import com.raji.domain.model.emaillist.EmailListItemModel

@Composable
fun EmailListScreen(
    state: EmailListContract.EmailListState, dispatch: (EmailListContract.EmailListEvent) -> Unit
) {

    when (state) {
        is EmailListContract.EmailListState.Error -> FullScreenError(
            errorMsg = state.error.toString(),
        )

        EmailListContract.EmailListState.Loading -> LinearFullScreenProgress(
            modifier = Modifier.semantics {
                contentDescription = "Loading"
            }
        )

        is EmailListContract.EmailListState.Success -> EmailListUi(
            state,
            dispatch
        )
    }
}

@Composable
fun EmailListUi(
    states: EmailListContract.EmailListState.Success,
    dispatch: (EmailListContract.EmailListEvent) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(
            states.emailList,
            key = { it.id }
        ) {
            EmailItem(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable {
                        dispatch.invoke(EmailListContract.EmailListEvent.EmailClicked(it))
                    },
                profileImageUrl = it.profileImage,
                senderName = it.from,
                emailSubject = it.subject,
                emailSnippet = it.snippet,
                isStarred = it.isStarred,
                date = it.date.toformattedDate()
            )
        }
    }
}
