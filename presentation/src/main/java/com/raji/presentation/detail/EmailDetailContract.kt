package com.raji.presentation.detail

import com.raji.core_ui.core.MVIContract
import com.raji.domain.model.emaildetails.EmailDetailsModel
import com.raji.presentation.detail.EmailDetailContract.DetailUiState
import com.raji.presentation.detail.EmailDetailContract.EmailDetailsEffect
import com.raji.presentation.detail.EmailDetailContract.EmailDetailsEvent

sealed interface EmailDetailContract :
    MVIContract<DetailUiState, EmailDetailsEffect, EmailDetailsEvent> {
    data class DetailUiState(
        val loading: Boolean = false,
        val emailDetail: EmailDetailsModel? = null,
        val isError: Boolean =false
    )

    sealed class EmailDetailsEffect {
        data object NavigateToDraftEmail : EmailDetailsEffect()
    }

    sealed class EmailDetailsEvent {

        data object LoadEmailDetails : EmailDetailsEvent()
        data object DeleteEmail : EmailDetailsEvent()
        data object ArchiveEmail : EmailDetailsEvent()
        data object NewEmail : EmailDetailsEvent()
        data object OnTopAppBarEmailMenu : EmailDetailsEvent()
        data object OnMarkAsImportant : EmailDetailsEvent()
        data object OnSendEmojiReply : EmailDetailsEvent()
        data object OnSendReply : EmailDetailsEvent()
        data object OnEmailMenu : EmailDetailsEvent()
        data object OnAttachFile : EmailDetailsEvent()
        data object OnReplyDropDown : EmailDetailsEvent()
        data object OnBottomBarEmojiReply : EmailDetailsEvent()
        data object OnBottomBarReply : EmailDetailsEvent()
    }
}