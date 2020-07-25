package ru.iandreyshev.navwithkoinscopestest.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_country_movies.*
import org.koin.core.parameter.parametersOf
import ru.iandreyshev.navwithkoinscopestest.R
import ru.iandreyshev.navwithkoinscopestest.data.IMovieRepository

class CountryMoviesFragment : BaseFragment(R.layout.fragment_country_movies) {

    private val mArgs by navArgs<CountryMoviesFragmentArgs>()
    private val mScope by lazy { getScope(R.id.nav_graph_country_movies) }
    private val mRepository by lazy {
        mScope.get<IMovieRepository> {
            parametersOf(mArgs.country)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        printInfo()
        printCounty()
        printMovies()

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun printInfo() {
        info.text = """
            Scope: ${mScope.id}
            Repo: ${mRepository.toString()}
        """.trimIndent()
    }

    private fun printCounty() {
        this.country.text = "${mArgs.country} movies"
    }

    private fun printMovies() {
        val adapter = Adapter { movie ->
            CountryMoviesFragmentDirections
                .actionCountryMoviesFragmentToMovieFragment(movieId = movie.id)
                .let(findNavController()::navigate)
        }
        recyclerView.adapter = adapter
        adapter.submitList(mRepository.getMovies())
    }

}