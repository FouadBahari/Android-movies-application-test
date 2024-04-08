package com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies

import com.example.androidmoviesapplicationtest.features.movies.domain.model.Movie


data class MovieListState(
    val isLoading: Boolean = false,
    val trendingMovieListPage: Int = 1,
    val isCurrentTrendingScreen: Boolean = true,
    val trendingMovieList: List<Movie> = emptyList(),
)