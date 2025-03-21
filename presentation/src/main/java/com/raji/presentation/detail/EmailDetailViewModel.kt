package com.raji.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raji.core.functional.fold
import com.raji.core_ui.functional.stateInWhileActive
import com.raji.domain.usecase.EmailDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailDetailViewModel @Inject constructor(val emailDetailUseCase: EmailDetailUseCase) :
    ViewModel(), EmailDetailContract {

    private val mutableUIState: MutableStateFlow<EmailDetailContract.DetailUiState> =
        MutableStateFlow(EmailDetailContract.DetailUiState())

    private val mutableSharedFlow: MutableSharedFlow<EmailDetailContract.EmailDetailsEffect> =
        MutableSharedFlow()

    override val state: StateFlow<EmailDetailContract.DetailUiState>
        get() = mutableUIState.stateInWhileActive(
            viewModelScope,
            EmailDetailContract.DetailUiState()
        ) {
            event(EmailDetailContract.EmailDetailsEvent.LoadEmailDetails)
        }
    override val effect: SharedFlow<EmailDetailContract.EmailDetailsEffect>
        get() = mutableSharedFlow.asSharedFlow()


    override fun event(event: EmailDetailContract.EmailDetailsEvent) {
        when (event) {
            EmailDetailContract.EmailDetailsEvent.ArchiveEmail -> TODO()
            EmailDetailContract.EmailDetailsEvent.DeleteEmail -> TODO()
            EmailDetailContract.EmailDetailsEvent.LoadEmailDetails -> loadEmailDetails()
            EmailDetailContract.EmailDetailsEvent.NewEmail -> TODO()
            EmailDetailContract.EmailDetailsEvent.OnAttachFile -> TODO()
            EmailDetailContract.EmailDetailsEvent.OnBottomBarEmojiReply -> TODO()
            EmailDetailContract.EmailDetailsEvent.OnBottomBarReply -> TODO()
            EmailDetailContract.EmailDetailsEvent.OnEmailMenu -> TODO()
            EmailDetailContract.EmailDetailsEvent.OnMarkAsImportant -> TODO()
            EmailDetailContract.EmailDetailsEvent.OnReplyDropDown -> TODO()
            EmailDetailContract.EmailDetailsEvent.OnSendEmojiReply -> TODO()
            EmailDetailContract.EmailDetailsEvent.OnSendReply -> TODO()
            EmailDetailContract.EmailDetailsEvent.OnTopAppBarEmailMenu -> TODO()
        }
    }

    private fun loadEmailDetails() {
        viewModelScope.launch {
            emailDetailUseCase().fold(
                onSuccess = {
                    mutableUIState.update { state ->
                        state.copy(emailDetail = it, loading = false, isError = false)
                    }
                },
                onError = {
                    mutableUIState.update { state ->
                        state.copy(emailDetail = null, isError = true, loading = false)
                    }

                }
            )
        }
    }
}