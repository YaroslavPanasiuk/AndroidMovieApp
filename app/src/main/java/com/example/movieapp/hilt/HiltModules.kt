package com.example.movieapp.hilt

import com.example.movieapp.remote.MovieDBInterface
import com.example.movieapp.ui.details.MovieDetailsRepository
import com.example.movieapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {
    @Provides
    fun provideRetrofitInterface(): MovieDBInterface{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDBInterface::class.java)
    }

    @Provides
    fun provideRepository(movieDBInterface: MovieDBInterface): MovieDetailsRepository{
        return MovieDetailsRepository(movieDBInterface)
    }


}