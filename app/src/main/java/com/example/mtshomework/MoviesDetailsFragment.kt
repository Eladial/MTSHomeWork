package com.example.mtshomework

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.*


class MovieDetailsFragment : Fragment() {
    private lateinit var adapter: MyMoviesAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recycler: RecyclerView
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, exc ->
        Log.d("coroutine", "$exc")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        recycler = view.findViewById(R.id.recycler)
        val movies = prepareMovies()
        adapter = MyMoviesAdapter(view.context, this::moviesListener, movies)
        recycler.adapter = adapter
        swipeRefresh = view.findViewById(R.id.swipe_container)
        swipeRefresh.setOnRefreshListener {
            updateMovies()
        }
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
        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
            Thread.sleep(2)
            val movies = prepareMovies().shuffled()
            val differ = DiffUtil.calculateDiff(MoviesCallback(adapter.getMovies(), movies))
            adapter.setMovies(movies)

            withContext(Dispatchers.Main) {
                differ.dispatchUpdatesTo(adapter)
                recycler.scrollToPosition(0)
                swipeRefresh.isRefreshing = false
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieDetailsFragment()
    }
}
