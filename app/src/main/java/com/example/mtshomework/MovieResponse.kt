package com.example.mtshomework

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PopularResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieResponse>,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int
)

@Parcelize
@Entity(tableName = "movies")
data class MovieResponse(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Long,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String,

    @SerializedName("vote_average")
    @ColumnInfo(name = "rate_score")
    val voteAverage: Float,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String
) : Parcelable
