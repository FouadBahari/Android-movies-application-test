package com.example.androidmoviesapplicationtest.features.trendingMovies.presentaion


sealed interface MovieListUiEvent {
    data class Paginate(val category: String) : MovieListUiEvent
    object Navigate : MovieListUiEvent
}