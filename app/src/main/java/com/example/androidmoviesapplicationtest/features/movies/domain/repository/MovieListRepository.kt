package com.example.androidmoviesapplicationtest.features.movies.domain.repository

import com.example.androidmoviesapplicationtest.features.movies.domain.model.Movie
import com.example.androidmoviesapplicationtest.core.util.Resource
import kotlinx.coroutines.flow.Flow


interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(
        id: Int): Flow<Resource<Movie>>
}