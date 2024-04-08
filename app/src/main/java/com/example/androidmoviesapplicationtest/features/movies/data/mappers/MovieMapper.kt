package com.example.androidmoviesapplicationtest.features.movies.data.mappers

import com.example.androidmoviesapplicationtest.features.movies.data.local.movie.MovieEntity
import com.example.androidmoviesapplicationtest.features.movies.data.remote.respnod.MovieDto
import com.example.androidmoviesapplicationtest.features.movies.domain.model.Movie



fun MovieDto.toMovieEntity(
): MovieEntity {
    return MovieEntity(
        adult = adult ?: false,
        backdrop_path = backdrop_path ?: "",
        original_language = original_language ?: "",
        overview = overview ?: "",
        poster_path = poster_path ?: "",
        release_date = release_date ?: "",
        title = title ?: "",
        vote_average = vote_average ?: 0.0,
        popularity = popularity ?: 0.0,
        vote_count = vote_count ?: 0,
        id = id ?: -1,

        original_title = original_title ?: "",
        video = video ?: false,

    )
}

fun MovieEntity.toMovie(
): Movie {
    return Movie(
        backdrop_path = backdrop_path,
        original_language = original_language,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        vote_average = vote_average,
        popularity = popularity,
        vote_count = vote_count,
        video = video,
        id = id,
        adult = adult,
        original_title = original_title,
    )
}




















