package com.example.mtshomework

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "full_description")
    val fullDescription: String,

    @ColumnInfo(name = "rate_score")
    val rateScore: Int,

    @ColumnInfo(name = "age_restriction")
    val ageRestriction: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String
): Parcelable {
    constructor(title: String, description: String, fullDescription: String, rateScore: Int, ageRestriction: Int, imageUrl: String):
            this(null, title, description, fullDescription, rateScore, ageRestriction, imageUrl)
}
