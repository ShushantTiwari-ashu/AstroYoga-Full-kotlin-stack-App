package com.shushant.astroyoga.horoscope.di

import com.shushant.astroyoga.horoscope.ui.HoroscopeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val horoscopeModule = module {
    viewModel { HoroscopeViewModel(get()) }
}