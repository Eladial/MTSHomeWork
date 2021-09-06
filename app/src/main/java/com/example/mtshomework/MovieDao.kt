package com.example.mtshomework

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<MovieResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieResponse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieResponse): Long

    @Update
    fun update(movie: MovieResponse)

    @Delete
    fun delete(movie: MovieResponse)

    @Query("DELETE FROM movies WHERE id = :movieId")
    fun deleteById(movieId: Long)

    @Query("DELETE FROM movies")
    fun deleteAll()

}