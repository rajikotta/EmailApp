package com.raji.data.remote

import com.raji.data.dto.detail.EmailDetailsDto
import com.raji.data.dto.list.EmailListItemDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("emaillist")
    suspend fun getEmailList(): Response<List<EmailListItemDto>>

    @GET("api/v1/emaildetails")
    suspend fun getEmailDetail(): Response<List<EmailDetailsDto>>
}
