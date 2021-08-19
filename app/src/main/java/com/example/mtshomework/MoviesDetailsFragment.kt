package com.example.mtshomework

import android.os.Bundle
import android.os.SystemClock
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
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recycler)
        val movies = prepareMovies()
        adapter = MyMoviesAdapter(view.context, this::moviesListener,
            movies as MutableList<MovieDto>
        )
        recycler.adapter = adapter
        swipeRefresh = view.findViewById(R.id.swipe_container)
        swipeRefresh.setOnRefreshListener {
            updateMovies()
        }
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
            SystemClock.sleep(2000)
            val movies = prepareMovies().shuffled()

            withContext(Dispatchers.Main) {
                val differ = DiffUtil.calculateDiff(MoviesCallback(adapter.getMovies(), movies))
                adapter.setMovies(movies)
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
