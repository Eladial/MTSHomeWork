package com.example.mtshomework

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDto(
    val id: Int,
    val title: String,
    val description: String,
    val full_description: String,
    val rateScore: Int,
    val ageRestriction: Int,
    val imageUrl: String
) : Parcelable
