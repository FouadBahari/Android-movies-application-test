package com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies


sealed interface MovieListUiEvent {
    object Paginate : MovieListUiEvent
    object Navigate : MovieListUiEvent

}