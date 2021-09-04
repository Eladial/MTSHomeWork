package com.example.mtshomework

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>

    @Insert
    fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie): Long

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("DELETE FROM movies WHERE id = :movieId")
    fun deleteById(movieId: Long)

    @Query("DELETE FROM movies")
    fun deleteAll()

}