package ru.iandreyshev.navwithkoinscopestest.data

import java.io.Serializable

inline class MovieId(val uid: String) : Serializable

data class Movie(
    val id: MovieId,
    val title: String,
    val country: Country
)

enum class Country {
    RU,
    USA,
    UK;
}

data class Actor(
    val name: String
)
