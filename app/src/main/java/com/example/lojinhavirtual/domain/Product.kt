package com.example.lojinhavirtual.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val desc: Int?,
    val coverUrl: String
) : Parcelable
