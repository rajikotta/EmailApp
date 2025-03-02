package com.raji.domain.usecase

import com.raji.domain.repository.EmailRepository
import javax.inject.Inject

class EmailDetailUseCase @Inject constructor(val repository: EmailRepository) {
    suspend operator fun invoke() = repository.getEmailDetail()
}