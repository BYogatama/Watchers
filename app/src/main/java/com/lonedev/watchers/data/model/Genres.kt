package com.lonedev.watchers.data.model


data class Genres(
    val genres: List<Genre> = listOf()
)

data class Genre(
    val id: Int = 0,
    val name: String = ""
)