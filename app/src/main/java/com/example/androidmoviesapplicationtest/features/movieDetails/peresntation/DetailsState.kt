package com.example.androidmoviesapplicationtest.features.movieDetails.peresntation

import com.example.androidmoviesapplicationtest.features.trendingMovies.domain.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)
