package com.shushant.astroyoga.onboard.di

import com.shushant.astroyoga.onboard.filldetails.UserDetailsViewModel
import com.shushant.astroyoga.onboard.filldetails.horoscopeanalyzer.HoroscopeAnalyzerViewModel
import com.shushant.astroyoga.onboard.filldetails.pob.PlaceOfBirthViewModel
import com.shushant.astroyoga.onboard.repo.HoroscopeAnalyzeRepositoryImpl
import com.shushant.astroyoga.onboard.repo.PlaceRepoImpl
import com.shushant.astroyoga.data.repo.HoroscopeAnalyzeRepository
import com.shushant.astroyoga.data.repo.PlaceRepository
import com.shushant.astroyoga.data.repo.UserRepository
import com.shushant.astroyoga.onboard.repo.UserRepositoryImpl
import com.shushant.astroyoga.onboard.usecase.UserDetailsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val onBoardingModule = module {
    viewModel { UserDetailsViewModel(get()) }
    viewModel { HoroscopeAnalyzerViewModel(get(), get()) }
    viewModel { PlaceOfBirthViewModel(get()) }
    factory<PlaceRepository> { PlaceRepoImpl(get()) }
    factory<UserRepository> { UserRepositoryImpl(get()) }
    factory { UserDetailsUseCase(get(),get()) }
    factory<HoroscopeAnalyzeRepository> { HoroscopeAnalyzeRepositoryImpl(get()) }
}