package com.demo.feature.movies.moviedetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailData(val title: String, val description: String, val image: String) :
    Parcelable
