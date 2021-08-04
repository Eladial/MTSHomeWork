package com.example.mtshomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieDetailsFragment : Fragment() {
    private lateinit var adapter: MyMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val movies = prepareMovies()
        adapter = MyMoviesAdapter(view.context, this::moviesListener, movies)
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

    private fun updateMovies(){
        CoroutineScope(Dispatchers.IO).launch() {
            Thread.sleep(2)
            val movies = prepareMovies()
            val differ = DiffUtil.calculateDiff(MoviesCallback(adapter.getMovies(), movies))
            adapter.setMovies(movies)

            withContext(Dispatchers.Main) {
                differ.dispatchUpdatesTo(adapter)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieDetailsFragment()
    }
}
