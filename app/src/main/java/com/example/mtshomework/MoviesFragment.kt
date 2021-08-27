package com.example.mtshomework

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.*


class MoviesFragment : Fragment() {
    private val movieViewModel = MoviesViewModel()
    private lateinit var adapter: MoviesAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recycler: RecyclerView
    private lateinit var navController: NavController

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, exc ->
        Log.d("coroutine", "$exc")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.recycler)
        val movies = prepareMovies()
        adapter = MoviesAdapter(view.context, this::moviesListener,
            movies.toMutableList()
        )
        recycler.adapter = adapter
        swipeRefresh = view.findViewById(R.id.swipe_container)
        swipeRefresh.setOnRefreshListener {
            updateMovies()
        }
        movieViewModel.movies.observe(viewLifecycleOwner, Observer(adapter::setMovies))
        navController = view.findNavController()
    }

    private fun prepareMovies(): List<MovieDto> {
        return MoviesDataSourceImpl().getMovies()
    }

    private fun moviesListener(id: Int) {
        val bundle = Bundle()
        bundle.putParcelable("movie_data", prepareMovies().find {it.id == id })
        navController.navigate(R.id.action_movieFragment_to_movieDetailsFragment, bundle)


    }

    private fun updateMovies(){
        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
            movieViewModel.updateMovies()

            withContext(Dispatchers.Main) {
                val differ = DiffUtil.calculateDiff(MoviesCallback(adapter.getMovies(), movieViewModel.movies.value!!))
                adapter.setMovies(movieViewModel.movies.value!!)
                differ.dispatchUpdatesTo(adapter)
                recycler.scrollToPosition(0)
                swipeRefresh.isRefreshing = false
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MoviesFragment()
    }
}
