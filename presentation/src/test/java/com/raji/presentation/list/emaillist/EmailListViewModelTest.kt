package com.raji.presentation.list.emaillist

import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import com.raji.domain.model.emaillist.EmailListItemModel
import com.raji.domain.usecase.EmailListUseCase
import com.raji.presentation.list.EmailListContract
import com.raji.presentation.list.EmailListViewModel
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class EmailListViewModelTest : FeatureSpec({
    val emailListUseCase: EmailListUseCase = mockk()
    val viewModel = EmailListViewModel(emailListUseCase)


    val testDispatcher = StandardTestDispatcher()

    beforeTest {
        Dispatchers.setMain(testDispatcher)
    }

    afterTest {
        Dispatchers.resetMain()
    }



    feature("LoadEmailList event") {
        scenario("should update state to Success when use case returns data") {
            val emailList = listOf(mockk<EmailListItemModel>())
            coEvery { emailListUseCase() } returns NetworkResult.Success(emailList)

            runTest {
                val states = mutableListOf<EmailListContract.EmailListState>()

                val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                    viewModel.state.toList(states)
                }
                viewModel.event(EmailListContract.EmailListEvent.LoadEmailList)
                testScheduler.advanceUntilIdle()
                collectJob.cancel()
                states shouldBe listOf(
                    EmailListContract.EmailListState.Loading,
                    EmailListContract.EmailListState.Success(emailList)
                )
            }
        }

        scenario("should update state to Error when use case returns failure") {
            val failure = Failure.ServerError(500, "Server Error")
            coEvery { emailListUseCase() } returns NetworkResult.Error(failure)

            runTest {
                val states = mutableListOf<EmailListContract.EmailListState>()

                val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                    viewModel.state.toList(states)
                }

                viewModel.event(EmailListContract.EmailListEvent.LoadEmailList)
                testScheduler.advanceUntilIdle()
                collectJob.cancel()
                states shouldBe listOf(
                    EmailListContract.EmailListState.Loading,
                    EmailListContract.EmailListState.Error(failure)
                )
            }
        }
    }

    feature("EmailClicked event") {
        scenario("should emit NavigateToEmailDetails effect") {
            val email = mockk<EmailListItemModel>()
            val event = EmailListContract.EmailListEvent.EmailClicked(email)

            runTest {
                val effects = mutableListOf<EmailListContract.EmailListEffect>()

                val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
                    viewModel.effect.collect { effect ->
                        effects.add(effect)
                    }
                }

                viewModel.event(event)
                testScheduler.advanceUntilIdle()
                collectJob.cancel()

                effects shouldBe listOf(
                    EmailListContract.EmailListEffect.NavigateToDetail(email)
                )
            }
        }
    }

})