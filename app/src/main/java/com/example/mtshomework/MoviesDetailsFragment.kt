package com.example.mtshomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView




class MovieDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val movies = prepareMovies()
        val adapter = MyMoviesAdapter(view.context, this::moviesListener, movies)
        recycler.adapter = adapter
        return view
    }

    private fun prepareMovies(): List<MovieDto> {
        return MoviesDataSourceImpl().getMovies()
    }

    private fun moviesListener(item: Int) {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().add(R.id.container_view, MovieFragment.newInstance(item), "FRAGMENT").addToBackStack(null).commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieDetailsFragment()
    }
}
