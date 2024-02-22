package com.example.movieapp.ui.details

import com.example.movieapp.data.movie_details.MovieDetails
import com.example.movieapp.remote.MovieDBInterface
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.Result
import com.example.movieapp.utils.Status

class MovieDetailsRepository(private val movieDBInterface: MovieDBInterface) {
    suspend fun getMovieDetails(id: Int):Result<MovieDetails>{
        return try {
            val response = movieDBInterface.getMovieDetails(id, Constants.API_KEY)
            if(response.isSuccessful){
                Result(Status.SUCCESS, response.body(), null)
            } else {
                Result(Status.ERROR, null, response.code().toString())
            }

        } catch (e: Exception){
            Result(Status.ERROR, null, "response exception")
        }
    }
}