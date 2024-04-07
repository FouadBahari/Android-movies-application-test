package com.example.androidmoviesapplicationtest.features.movies.presentaion.movieDetails

import com.example.androidmoviesapplicationtest.features.movies.domain.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)
