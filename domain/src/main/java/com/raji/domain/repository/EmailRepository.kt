package com.raji.domain.repository

import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import com.raji.domain.model.emaildetails.EmailDetailsModel
import com.raji.domain.model.emaillist.EmailListItemModel

interface EmailRepository {
    suspend fun getEmailList(): NetworkResult<List<EmailListItemModel>, Failure>
    suspend fun getEmailDetail(): NetworkResult<EmailDetailsModel, Failure>
}