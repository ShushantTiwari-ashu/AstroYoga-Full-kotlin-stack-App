package com.shushant.navigation

import androidx.navigation.NamedNavArgument

object Graph {
    const val ROOT = "root_graph"
    const val SPLASH = "splash_graph"
    const val ONBOARDING = "onboarding_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}


fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
    val mandatoryArguments =
        navArguments.filter { it.argument.defaultValue == null }.takeIf { it.isNotEmpty() }
            ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }.orEmpty()
    val optionalArguments =
        navArguments.filter { it.argument.defaultValue != null }.takeIf { it.isNotEmpty() }
            ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }.orEmpty()
    return "$this$mandatoryArguments$optionalArguments"
}