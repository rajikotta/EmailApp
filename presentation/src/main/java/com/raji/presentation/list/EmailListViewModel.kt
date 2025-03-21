package com.raji.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raji.core.functional.fold
import com.raji.core_ui.functional.stateInWhileActive
import com.raji.domain.usecase.EmailListUseCase
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
class EmailListViewModel @Inject constructor(private val emailListUseCase: EmailListUseCase) :
    ViewModel(),
    EmailListContract {


    private val _state =
        MutableStateFlow<EmailListContract.EmailListState>(EmailListContract.EmailListState.Loading)
    private val _effect = MutableSharedFlow<EmailListContract.EmailListEffect>()

    override val state: StateFlow<EmailListContract.EmailListState>
        get() = _state.stateInWhileActive(
            scope = viewModelScope,
            initial = EmailListContract.EmailListState.Loading
        ) {
            event(EmailListContract.EmailListEvent.LoadEmailList)

        }

    override
    val effect: SharedFlow<EmailListContract.EmailListEffect>
        get() = _effect.asSharedFlow()

    override fun event(event: EmailListContract.EmailListEvent) {

        when (event) {
            is EmailListContract.EmailListEvent.EmailClicked -> {
                viewModelScope.launch {

                    _effect.emit(EmailListContract.EmailListEffect.NavigateToDetail(event.model))

                }
            }

            EmailListContract.EmailListEvent.LoadEmailList -> {
//                updateState(EmailListContract.EmailListState.Loading)
                loadEmail()
            }
        }
    }


    private fun loadEmail() {

        viewModelScope.launch {
            emailListUseCase().fold(
                onSuccess = {
                    updateState(EmailListContract.EmailListState.Success(emailList = it))
                },
                onError = {
                    updateState(EmailListContract.EmailListState.Error(it))
                }
            )
        }
    }


    private fun updateState(state: EmailListContract.EmailListState) {
        _state.update {
            state
        }
    }
}