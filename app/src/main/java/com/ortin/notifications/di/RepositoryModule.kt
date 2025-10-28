package com.ortin.notifications.di

import com.ortin.notifications.data.datastore.UserPreferences
import com.ortin.notifications.data.impl.AuthorizationRepositoryImpl
import com.ortin.notifications.data.mock.MockNotificationRepository
import com.ortin.notifications.domain.repository.AuthorizationRepository
import com.ortin.notifications.domain.repository.NotificationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserPreferences(get()) }
    single<AuthorizationRepository> { AuthorizationRepositoryImpl(get(), get()) }
    single<NotificationRepository> { /* TODO: replace with real implementation before merging */ MockNotificationRepository() }
}
