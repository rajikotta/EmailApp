package com.raji.data.di

import com.raji.data.repository.EmailRepositoryImpl
import com.raji.domain.repository.EmailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repository: EmailRepositoryImpl): EmailRepository
}