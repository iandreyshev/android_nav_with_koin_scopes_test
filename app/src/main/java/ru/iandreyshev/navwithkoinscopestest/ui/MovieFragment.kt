package ru.iandreyshev.navwithkoinscopestest.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_movie.*
import ru.iandreyshev.navwithkoinscopestest.R
import ru.iandreyshev.navwithkoinscopestest.data.IMovieRepository

class MovieFragment : BaseFragment(R.layout.fragment_movie) {

    private val mArgs by navArgs<MovieFragmentArgs>()
    private val mScope by lazy { getScope(R.id.nav_graph_country_movies) }
    private val mRepository by lazy { mScope.get<IMovieRepository>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        printInfo()
        printMovie()

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun printInfo() {
        info.text = """
            Scope: ${mScope.id}
            Repo: $mRepository
        """.trimIndent()
    }

    private fun printMovie() {
        val movie = mRepository.getMovies()
            .first { movie -> movie.id == mArgs.movieId }

        movieTitle.text = """
            Id: ${movie.id}
            Movie: ${movie.title}
            Country: ${movie.country}
        """.trimIndent()
    }

}
