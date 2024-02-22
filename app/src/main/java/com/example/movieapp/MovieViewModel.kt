package com.example.movieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.movieapp.data.movie_details.MovieDetails
import com.example.movieapp.remote.MovieDBInterface
import com.example.movieapp.ui.details.MovieDetailsRepository
import com.example.movieapp.ui.movies.MoviePaging
import com.example.movieapp.utils.Constants
import com.example.movieapp.utils.Events
import com.example.movieapp.utils.Status
import com.example.movieapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieDBInterface: MovieDBInterface, private val repository: MovieDetailsRepository): ViewModel() {

    val popularMovies = Pager(PagingConfig(pageSize = Constants.PAGE_SIZE)){
        MoviePaging("popular", movieDBInterface)
    }.liveData.cachedIn(viewModelScope)

    val upcomingMovies = Pager(PagingConfig(pageSize = Constants.PAGE_SIZE)){
        MoviePaging("upcoming", movieDBInterface)
    }.liveData.cachedIn(viewModelScope)

    val nowPlayingMovies = Pager(PagingConfig(pageSize = Constants.PAGE_SIZE)){
        MoviePaging("now_playing", movieDBInterface)
    }.liveData.cachedIn(viewModelScope)
    val topRatedMovies = Pager(PagingConfig(pageSize = Constants.PAGE_SIZE)){
        MoviePaging("top_rated", movieDBInterface)
    }.liveData.cachedIn(viewModelScope)


    private val _movieDetails = MutableLiveData<Events<Result<MovieDetails>>>()
    val movieDetails: LiveData<Events<Result<MovieDetails>>> = _movieDetails

    fun getMovieDetails(id: Int) = viewModelScope.launch {
        _movieDetails.postValue(Events(Result(Status.LOADING, null, null)))
        _movieDetails.postValue(Events(repository.getMovieDetails(id)))
    }

}