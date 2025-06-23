package com.raji.data.repository

import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import com.raji.data.dto.detail.EmailDetailsDto
import com.raji.data.dto.list.EmailListItemDto
import com.raji.data.mapper.EmailDetailsMapper
import com.raji.data.mapper.EmailListMapper
import com.raji.data.remote.ApiService
import com.raji.domain.model.emaildetails.EmailDetailsModel
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import retrofit2.Response
import java.io.IOException

class EmailRepositoryImplTest : FunSpec() {
    private val apiService: ApiService = mockk()
    private val emailListMapper: EmailListMapper = mockk()
    private val emailDetailsMapper: EmailDetailsMapper = mockk()
    private val emailRepository =
        EmailRepositoryImpl(apiService, emailListMapper, emailDetailsMapper)

    init {

        test("getEmailList should return NetworkError for IOException") {
            val exp = IOException("Network Error")
            coEvery { apiService.getEmailList() } throws exp

            val result = emailRepository.getEmailList()
            result shouldBe NetworkResult.Error(Failure.NetworkError(exp))
        }


        test("getEmailList should return mapped data on success") {
            val mockResponse = Response.success(listOf<EmailListItemDto>())
            coEvery { apiService.getEmailList() } returns mockResponse
            coEvery { emailListMapper.map(any()) } returns listOf()

            val result = emailRepository.getEmailList()
            result shouldBe NetworkResult.Success(listOf())
        }

        test("getEmailDetails should return NetworkError for IOException") {
            val exp = IOException("Network Error")
            coEvery { apiService.getEmailDetail() } throws exp

            val result = emailRepository.getEmailDetail()
            result shouldBe NetworkResult.Error(Failure.NetworkError(exp))
        }

        test("getEmailDetails should return mapped data on success") {
            val mockResponse = Response.success(listOf<EmailDetailsDto>())
            val mockKEmailDetailsModel = mockk<EmailDetailsModel>()
            coEvery { apiService.getEmailDetail() } returns mockResponse
            coEvery { emailDetailsMapper.map(any()) } returns mockKEmailDetailsModel

            val result = emailRepository.getEmailDetail()
            result shouldBe NetworkResult.Success(mockKEmailDetailsModel)
        }

    }


}