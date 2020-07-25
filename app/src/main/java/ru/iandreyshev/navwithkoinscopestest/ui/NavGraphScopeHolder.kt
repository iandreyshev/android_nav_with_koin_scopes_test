package ru.iandreyshev.navwithkoinscopestest.ui

import androidx.lifecycle.ViewModel
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeID
import org.koin.java.KoinJavaComponent
import timber.log.Timber

class NavGraphScopeHolder(
    private val navGraphId: Int
) : ViewModel() {

    val scope: Scope by lazy {
        KoinJavaComponent.getKoin().getOrCreateScope(
            navGraphId.navScopeId,
            navGraphId.navQualifier
        ).also {
            Timber.d("Create scope: $it")
        }
    }

    override fun onCleared() {
        Timber.d("Close scope: $scope")
        scope.close()
    }

}

val Int.navQualifier: Qualifier
    get() = named("FlowQualifier (NavGraphId@$this)")

fun navQualifier(navGraphId: Int) =
    navGraphId.navQualifier

val Int.navScopeId: ScopeID
    get() = "ScopeId (NavGraphId@${this})"

