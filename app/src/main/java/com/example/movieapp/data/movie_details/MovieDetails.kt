package com.example.movieapp.data.movie_details


import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val budget: Int,
    val homepage: String,
    val id: Int,
    @SerializedName("overview")
    val description: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val rating: Double,
)