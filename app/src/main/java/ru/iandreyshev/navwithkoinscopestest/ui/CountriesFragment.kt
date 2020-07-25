package ru.iandreyshev.navwithkoinscopestest.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.android.synthetic.main.fragment_countries.info
import ru.iandreyshev.navwithkoinscopestest.R
import ru.iandreyshev.navwithkoinscopestest.data.Country
import ru.iandreyshev.navwithkoinscopestest.data.IMovieRepository

class CountriesFragment : BaseFragment(R.layout.fragment_countries) {

    private val mScope by lazy { getScope(R.id.nav_graph_main) }
    private val mRepository by lazy { mScope.get<IMovieRepository>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        printInfo()
        initButtons()
    }

    private fun printInfo() {
        info.text = """
            Scope: ${mScope.id}
            Repo: $mRepository
        """.trimIndent()
    }

    private fun initButtons() {
        ruMovies.text = "${Country.RU} (${mRepository.countOf(Country.RU)})"
        ruMovies.setOnClickListener {
            navigateToMoviesOf(Country.RU)
        }

        usaMovies.text = "${Country.USA} (${mRepository.countOf(Country.USA)})"
        usaMovies.setOnClickListener {
            navigateToMoviesOf(Country.USA)
        }

        ukMovies.text = "${Country.UK} (${mRepository.countOf(Country.UK)})"
        ukMovies.setOnClickListener {
            navigateToMoviesOf(Country.UK)
        }
    }

    private fun navigateToMoviesOf(country: Country) {
        CountriesFragmentDirections
            .actionMovieFragmentToNavMovie(country)
            .let(findNavController()::navigate)
    }

    private fun IMovieRepository.countOf(country: Country) =
        getMovies().count { it.country == country }

}
