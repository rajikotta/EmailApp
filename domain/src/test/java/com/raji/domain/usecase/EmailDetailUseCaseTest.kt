package com.raji.domain.usecase

import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import com.raji.domain.model.emaildetails.EmailDetailsModel
import com.raji.domain.repository.EmailRepository
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import java.io.IOException


class EmailDetailUseCaseTest : ShouldSpec({
    val repository = mockk<EmailRepository>()
    val usecase: EmailDetailUseCase = EmailDetailUseCase(repository)


    should("return NetworkError for IOException") {
        val exception = IOException("Network Error")
        coEvery { repository.getEmailDetail() } returns NetworkResult.Error(
            Failure.NetworkError(
                exception
            )
        )
        val result = usecase()

        result shouldBe NetworkResult.Error(Failure.NetworkError(exception))
    }
    should("return ServerError for HttpException") {
        coEvery { repository.getEmailDetail() } returns NetworkResult.Error(
            Failure.ServerError(
                200,
                "server"
            )
        )

        val result = usecase.invoke()

        result shouldBe NetworkResult.Error(Failure.ServerError(200, "server"))
    }

    should("return email details on success") {
        val emailDetails = mockk<EmailDetailsModel>()
        coEvery { repository.getEmailDetail() } returns NetworkResult.Success(emailDetails)

        val result = usecase.invoke()

        result shouldBe NetworkResult.Success(emailDetails)
    }
})