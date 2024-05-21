package com.azrinurvani.latihanhiltmvvm.domain.model

//TODO - Step 13
data class News(

    val id: Int = 0,

    val name: String,

    val title: String,

    val description: String,

    val url: String,

    val urlToImage: String,

    val publishedAt: String
)