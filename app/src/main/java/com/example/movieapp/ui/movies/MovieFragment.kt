package com.example.movieapp.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.MovieViewModel
import com.example.movieapp.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    val viewModel: MovieViewModel by viewModels()

    private val popularMoviesAdapter = MoviePagingAdapter()
    private val upcomingMoviesAdapter = MoviePagingAdapter()
    private val nowPlayingMoviesAdapter = MoviePagingAdapter()
    private val topRatedMoviesAdapter = MoviePagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        serRecyclerViews()

        popularMoviesAdapter.onMovieClicked { goToMovieDetails(it) }
        upcomingMoviesAdapter.onMovieClicked { goToMovieDetails(it) }
        nowPlayingMoviesAdapter.onMovieClicked { goToMovieDetails(it) }
        topRatedMoviesAdapter.onMovieClicked { goToMovieDetails(it) }

        viewModel.popularMovies.observe(viewLifecycleOwner){
            popularMoviesAdapter.submitData(lifecycle, it)
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner){
            upcomingMoviesAdapter.submitData(lifecycle, it)
        }

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner){
            nowPlayingMoviesAdapter.submitData(lifecycle, it)
        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner){
            topRatedMoviesAdapter.submitData(lifecycle, it)
        }
    }

    private fun serRecyclerViews(){
        binding.popularMovieRecycler.apply {
            adapter = popularMoviesAdapter
            setLinearLayout()
        }
        binding.upcomingMovieRecycler.apply {
            adapter = upcomingMoviesAdapter
            setLinearLayout()
        }
        binding.nowPlayingMovieRecycler.apply {
            adapter = nowPlayingMoviesAdapter
            setLinearLayout()
        }
        binding.topRetedMovieRecycler.apply {
            adapter = topRatedMoviesAdapter
            setLinearLayout()
        }
    }

    private fun RecyclerView.setLinearLayout(){
        val linearLayout = LinearLayoutManager(requireContext())
        linearLayout.orientation = LinearLayoutManager.HORIZONTAL
        this.layoutManager = linearLayout
    }

    private fun goToMovieDetails(movieId:Int){
        val action = MovieFragmentDirections.actionMovieFragmentToDetailsFragment(movieId)
        findNavController().navigate(action)
    }

}