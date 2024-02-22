package com.example.movieapp.remote

import com.example.movieapp.data.MovieResponse
import com.example.movieapp.data.movie_details.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBInterface {
    @GET("movie/{category}")
    suspend fun getAllMovies(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key")apiKey: String
    ): Response<MovieResponse>
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")id: Int,
        @Query("api_key")apiKey: String
    ): Response<MovieDetails>
}