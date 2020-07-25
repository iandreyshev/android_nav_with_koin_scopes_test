package ru.iandreyshev.navwithkoinscopestest.data

class MovieRepository : IMovieRepository {

    override fun getMovies(): List<Movie> = Movies

    override fun toString(): String {
        return "MovieRepository: ${hashCode()}"
    }

}
