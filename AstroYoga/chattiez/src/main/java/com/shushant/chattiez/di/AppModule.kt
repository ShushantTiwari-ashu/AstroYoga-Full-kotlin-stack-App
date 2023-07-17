package com.shushant.chattiez.di

import com.shushant.chattiez.data.utils.AppCoroutineDispatchers
import com.shushant.chattiez.operator.DefaultAppCoroutineDispatchers
import com.shushant.chattiez.ui.MainViewModel
import com.shushant.common.compose.utils.ActivityProvider
import com.shushant.resource.ResourceProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get(), get(), get(), get()) }
    single(createdAtStart = true) { ActivityProvider(get()) }
    single<AppCoroutineDispatchers> { DefaultAppCoroutineDispatchers() }
    single { ResourceProvider(get()) }
}