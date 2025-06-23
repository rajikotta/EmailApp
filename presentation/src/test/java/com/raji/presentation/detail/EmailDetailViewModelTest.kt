package com.raji.presentation.detail

import app.cash.turbine.test
import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import com.raji.domain.model.emaildetails.EmailDetailsModel
import com.raji.domain.usecase.EmailDetailUseCase
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

class EmailDetailViewModelTest : DescribeSpec({

    val emailDetailsUseCase: EmailDetailUseCase = mockk()
    val viewModel = EmailDetailViewModel(emailDetailsUseCase)

    val testDispatcher = StandardTestDispatcher()

    beforeTest {
        Dispatchers.setMain(testDispatcher)
    }

    afterTest {
        Dispatchers.resetMain()
    }


    describe("EmailDetailsViewModel") {
        it("should update state to Success when use case returns data") {
            val emailDetails = mockk<EmailDetailsModel>()
            coEvery { emailDetailsUseCase() } returns NetworkResult.Success(emailDetails)

            runTest {
                viewModel.state.test {
                    viewModel.event(EmailDetailContract.EmailDetailsEvent.LoadEmailDetails)

                    awaitItem() shouldBe EmailDetailContract.DetailUiState(loading = true)
                    awaitItem() shouldBe EmailDetailContract.DetailUiState(
                        emailDetail = emailDetails,
                        loading = false
                    )
                }
            }

        }



        it("should update state to Error when use case returns failure") {
            val failure = Failure.ServerError(500, "Server Error")
            coEvery { emailDetailsUseCase() } returns NetworkResult.Error(failure)

            runTest {
                viewModel.state.test {
                    viewModel.event(EmailDetailContract.EmailDetailsEvent.LoadEmailDetails)

                    awaitItem() shouldBe EmailDetailContract.DetailUiState(loading = true)
                    awaitItem() shouldBe EmailDetailContract.DetailUiState(
                        emailDetail = null,
                        loading = false,
                        isError = true
                    )
                }
            }
        }


    }


})


