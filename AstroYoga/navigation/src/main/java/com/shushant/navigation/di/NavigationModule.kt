package com.shushant.navigation.di

import com.shushant.navigation.AppComposeNavigator
import com.shushant.navigation.AstraNavigation
import org.koin.dsl.module

val navigationModule = module {
    single<AppComposeNavigator> { AstraNavigation() }
}