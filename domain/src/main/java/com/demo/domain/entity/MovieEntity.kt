package com.demo.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieEntity(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val category: String
) : Parcelable