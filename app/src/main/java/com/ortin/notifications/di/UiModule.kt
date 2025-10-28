package com.ortin.notifications.di

import com.ortin.notifications.presentation.auth.AuthorizationViewModel
import com.ortin.notifications.presentation.detail.DetailScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val uiModule = module {
    viewModel { AuthorizationViewModel(get()) }
    viewModel { DetailScreenViewModel() }
}
