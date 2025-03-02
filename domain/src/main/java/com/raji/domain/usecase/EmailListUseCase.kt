package com.raji.domain.usecase

import com.raji.core.error.Failure
import com.raji.core.functional.NetworkResult
import com.raji.domain.model.emaillist.EmailListItemModel
import com.raji.domain.repository.EmailRepository
import javax.inject.Inject

class EmailListUseCase @Inject constructor(val repository: EmailRepository) {

    suspend operator fun invoke(): NetworkResult<List<EmailListItemModel>, Failure> {
        return repository.getEmailList()
    }
}