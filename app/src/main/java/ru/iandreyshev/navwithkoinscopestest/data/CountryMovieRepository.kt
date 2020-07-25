package ru.iandreyshev.navwithkoinscopestest.data

class CountryMovieRepository(
    private val country: Country
) : IMovieRepository {

    override fun getMovies(): List<Movie> {
        return Movies.filter { it.country == country }
    }

    override fun toString(): String {
        return "CountryMovieRepository: ${hashCode()}"
    }

}
