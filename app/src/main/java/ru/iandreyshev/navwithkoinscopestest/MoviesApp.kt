package ru.iandreyshev.navwithkoinscopestest

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.iandreyshev.navwithkoinscopestest.data.CountryMovieRepository
import ru.iandreyshev.navwithkoinscopestest.data.IMovieRepository
import ru.iandreyshev.navwithkoinscopestest.data.MovieRepository
import ru.iandreyshev.navwithkoinscopestest.ui.navQualifier

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(module {
                scope(navQualifier(R.id.nav_graph_main)) {
                    scoped<IMovieRepository> {
                        MovieRepository()
                    }
                }
                scope(navQualifier(R.id.nav_graph_country_movies)) {
                    scoped<IMovieRepository> { parameters ->
                        CountryMovieRepository(parameters.component1())
                    }
                }
            })
        }
    }

}