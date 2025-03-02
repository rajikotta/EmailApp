package com.raji.data.repository

import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import com.raji.data.mapper.EmailDetailsMapper
import com.raji.data.mapper.EmailListMapper
import com.raji.data.remote.ApiService
import com.raji.data.remote.safeCall
import com.raji.domain.model.emaildetails.EmailDetailsModel
import com.raji.domain.model.emaillist.EmailListItemModel
import com.raji.domain.repository.EmailRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val emailListMapper: EmailListMapper,
    private val emailDetailsMapper: EmailDetailsMapper
) : EmailRepository {
    override suspend fun getEmailList(): NetworkResult<List<EmailListItemModel>, Failure> {
        return safeCall(
            apiCall = { apiService.getEmailList() },
            mapper = emailListMapper
        )

    }

    override suspend fun getEmailDetail(): NetworkResult<EmailDetailsModel, Failure> {
        return safeCall(
            apiCall = { apiService.getEmailDetail() },
            mapper = emailDetailsMapper
        )
    }
}

