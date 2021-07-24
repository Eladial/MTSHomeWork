package com.example.mtshomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_movie_details)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val movies = prepareMovies()
        val adapter = MyMoviesAdapter(this, this::moviesListener, movies)
        recycler.adapter = adapter
    }

    private fun prepareMovies(): List<MovieDto> {
        return MoviesDataSourceImpl().getMovies()
    }

    private fun moviesListener(item: MovieDto) {
        Toast.makeText(this, "Название фильма: ${item.title}", Toast.LENGTH_LONG).show()
    }

}