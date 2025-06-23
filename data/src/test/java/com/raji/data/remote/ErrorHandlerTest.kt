package com.raji.data.remote

import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import retrofit2.HttpException
import java.io.IOException

class ErrorHandlerTest : StringSpec({

    "test toEither with IOException" {
        val exception = IOException("Network Error")
        val either = exception.toNetworkResult()
        either shouldBe NetworkResult.Error(Failure.NetworkError(exception))
    }

    "test toEither with HttpException" {
        val exception = mockk<HttpException>().apply {
            every { code() } returns 404
            every { message() } returns "Not found"
        }


        val either = exception.toNetworkResult()
        either shouldBe NetworkResult.Error(Failure.ServerError(404, "Not found"))
    }


}


) {
}