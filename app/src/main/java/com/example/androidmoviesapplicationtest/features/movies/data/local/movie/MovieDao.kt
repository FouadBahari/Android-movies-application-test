package com.example.androidmoviesapplicationtest.features.movies.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * FROM MovieEntity WHERE title = :query")
    suspend fun searchMovies(query: String): List<MovieEntity>


    @Query("SELECT * FROM MovieEntity")
    suspend fun getMovieList(): List<MovieEntity>
}














