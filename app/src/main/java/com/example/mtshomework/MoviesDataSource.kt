package com.example.mtshomework


interface MoviesDataSource {
    fun getMovies(): List<Movie>
}