package com.ortin.notifications.di

import com.ortin.notifications.domain.usecase.LoginUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    single { LoginUseCase(get()) }
}
