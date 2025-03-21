package com.raji.presentation.list

import com.raji.core.error.Failure
import com.raji.core_ui.core.MVIContract
import com.raji.domain.model.emaillist.EmailListItemModel
import com.raji.presentation.list.EmailListContract.EmailListEffect
import com.raji.presentation.list.EmailListContract.EmailListEvent
import com.raji.presentation.list.EmailListContract.EmailListState

interface EmailListContract : MVIContract<EmailListState, EmailListEffect, EmailListEvent> {

    sealed class EmailListEvent {
        data object LoadEmailList : EmailListEvent()
        data class EmailClicked(val model: EmailListItemModel) : EmailListEvent()
    }


    sealed class EmailListState {
        data object Loading : EmailListState()

        data class Success(
            val emailList: List<EmailListItemModel>
        ) : EmailListState()

        data class Error(
            val error: Failure
        ) : EmailListState()
    }


    sealed interface EmailListEffect {
        data class NavigateToDetail(val email: EmailListItemModel) : EmailListEffect
    }

}