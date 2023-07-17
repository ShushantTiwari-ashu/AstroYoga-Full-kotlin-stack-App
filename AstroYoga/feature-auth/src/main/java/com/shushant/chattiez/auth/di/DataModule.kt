package com.shushant.astroyoga.auth.di

import com.shushant.astroyoga.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    viewModel { LoginViewModel(get()) }
}