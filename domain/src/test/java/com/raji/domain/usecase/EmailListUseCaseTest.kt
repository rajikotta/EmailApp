package com.raji.domain.usecase

import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import com.raji.domain.model.emaillist.EmailListItemModel
import com.raji.domain.repository.EmailRepository
import io.kotest.core.spec.style.AnnotationSpec
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException

class EmailListUseCaseTest {

    private val repository: EmailRepository = mockk()

    private lateinit var emailListUseCase: EmailListUseCase

    @BeforeEach
    fun setUp() {
        emailListUseCase = EmailListUseCase(repository)
    }

    @Test
    fun `Given ioexception occurs When use case is invoked then NetWorkError is returned`() =
        runTest {

            val exception = IOException("Network Error")

            coEvery { repository.getEmailList() } returns NetworkResult.Error(
                Failure.NetworkError(
                    exception
                )
            )
            assert(emailListUseCase() == NetworkResult.Error(Failure.NetworkError(exception)))

        }

    @Test
    fun `GIVEN HttpException occurs WHEN use case is invoked THEN ServerError returned`() =
        runTest {
            coEvery { repository.getEmailList() } returns NetworkResult.Error(
                Failure.ServerError(
                    200,
                    "server"
                )
            )

            assert(emailListUseCase() == NetworkResult.Error(Failure.ServerError(200, "server")))
        }

    @Test
    fun `GIVEN successful response WHEN use case is invoked THEN list of EmailListItemModel returned`() =
        runTest {
            val emailDetails = mockk<EmailListItemModel>()

            coEvery { repository.getEmailList() } returns NetworkResult.Success(listOf(emailDetails))

            assert(emailListUseCase() == NetworkResult.Success(listOf(emailDetails)))
        }
}