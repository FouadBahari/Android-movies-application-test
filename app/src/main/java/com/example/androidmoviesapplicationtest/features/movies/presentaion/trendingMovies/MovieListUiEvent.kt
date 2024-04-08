package com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies


sealed interface MovieListUiEvent {
    object Paginate : MovieListUiEvent
    data class Search(val query: String) : MovieListUiEvent
    object Navigate : MovieListUiEvent

}