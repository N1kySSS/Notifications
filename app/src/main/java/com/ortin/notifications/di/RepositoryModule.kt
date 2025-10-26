package com.ortin.notifications.di

import com.ortin.notifications.data.datastore.UserPreferences
import com.ortin.notifications.data.impl.AuthorizationRepositoryImpl
import com.ortin.notifications.domain.repository.AuthorizationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserPreferences(get()) }
    single<AuthorizationRepository> { AuthorizationRepositoryImpl(get()) }
}
