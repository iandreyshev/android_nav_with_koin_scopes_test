package ru.iandreyshev.navwithkoinscopestest.data

object Movies : List<Movie> by listOf(
    Movie(
        id = MovieId("0"),
        title = "Лето",
        country = Country.RU
    ),
    Movie(
        id = MovieId("1"),
        title = "Курьер",
        country = Country.RU
    ),
    Movie(
        id = MovieId("2"),
        title = "Экипаж",
        country = Country.RU
    ),
    Movie(
        id = MovieId("3"),
        title = "Американский Пирог",
        country = Country.USA
    ),
    Movie(
        id = MovieId("4"),
        title = "Джобс. Империя соблазна",
        country = Country.USA
    ),
    Movie(
        id = MovieId("5"),
        title = "Социальная сеть",
        country = Country.USA
    ),
    Movie(
        id = MovieId("6"),
        title = "Типа крутые легавые",
        country = Country.UK
    ),
    Movie(
        id = MovieId("7"),
        title = "Скотт Пилигрим против всех",
        country = Country.UK
    ),
    Movie(
        id = MovieId("8"),
        title = "Темный рыцарь",
        country = Country.UK
    )
)
