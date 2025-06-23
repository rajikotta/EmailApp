package com.raji.data.remote

import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import com.raji.core.mapper.ResultMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.io.IOException

class NetworkRequestDispatcherTest : BehaviorSpec({

    val apiCall = mockk<suspend () -> Response<String>>()
    val mapper = mockk<ResultMapper<String, String>>()

    Given("a successful API call") {
        coEvery { apiCall() } returns Response.success("Success")
        coEvery { mapper.map("Success") } returns "MappedSuccess"

        When("safeApiCall is invoked") {
            val result = safeCall(Dispatchers.Unconfined, apiCall, mapper)

            Then("it should return the mapped result") {
                result shouldBe NetworkResult.Success("MappedSuccess")
            }
        }
    }

    Given("an API call that returns an error response") {
        coEvery { apiCall() } returns Response.error(500, mockk(relaxed = true))

        When("safeApiCall is invoked") {
            val result = safeCall(Dispatchers.Unconfined, apiCall, mapper)

            Then("it should return a ServerError") {
                result shouldBe NetworkResult.Error(Failure.ServerError(500, "Response.error()"))
            }
        }
    }

    Given("an API call that throws an exception") {
        val exception = IOException("Network Error")
        coEvery { apiCall() } throws exception

        When("safeApiCall is invoked") {
            val result = safeCall(Dispatchers.Unconfined, apiCall, mapper)

            Then("it should return a NetworkError") {
                result shouldBe NetworkResult.Error(Failure.NetworkError(exception))
            }
        }
    }



    Given("body is null") {
        coEvery { apiCall() } returns Response.success(null)

        When("safeApiCall is invoked") {
            val result = safeCall(Dispatchers.Unconfined, apiCall, mapper)

            Then("it should return a ServerError") {
                result shouldBe NetworkResult.Error(Failure.ServerError(200, "OK"))
            }
        }
    }


})