package com.example.androidmoviesapplicationtest.core.util


sealed class Screen(val rout: String) {
    object Home : Screen("main")
    object TrendingMoviesList : Screen("trendingMovies")
    object Settings : Screen("settings")
    object Details : Screen("details")
}
