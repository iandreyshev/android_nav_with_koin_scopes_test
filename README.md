# Koin scope with navigation component

В этом примере я попробовал привязать скоупы из Koin к графам навигации из Jetpack Navigation Component

### Что из себя представляет приложение
Приложение со списком фильмов, которые отсортированые по странам

В приложении есть 3 экрана
* CountriesFragment - выбор страны фильмов
* CountryMoviesFragment - список фильмов для страны
* MovieFragment - описание фильма

Упрощенная диаграмма приложения:

  <img src="images/scope_diagram.png" height="300" >

### Скоупы
Мне хотелось привязать скоупы к графам навигации, чтобы делать для отдельных фич свои графы навигации, в которых будут свои скоупы. Скоупы хранятся внутри экземпляров класса NavGraphScopeHolder. NavGraphScopeHolder является ViewModel (из Jetpack). Это необходимо, чтобы получить экземпляр NavGraphScopeHolder привязанный к текущему графу навигации с помощью метода `navGraphViewModels()`. Скоупы могут быть получены из фрагмента с помощью базового класса BaseFragment. Внутри метода getScope достается экземпляр NavGraphScopeHolder и из него получается Scope.

```kt
abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected fun getScope(@IdRes navGraphId: Int): Scope {
        val holder by navGraphViewModels<NavGraphScopeHolder>(navGraphId) {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    NavGraphScopeHolder(navGraphId) as T
            }
        }

        return holder.scope
    }

}

class NavGraphScopeHolder(private val navGraphId: Int) : ViewModel() {

    val scope: Scope by lazy {
        KoinJavaComponent.getKoin().getOrCreateScope(
            navGraphId.navScopeId,
            navGraphId.navQualifier
        )
    }

    override fun onCleared() {
        scope.close()
    }

}

```

 ScopeID и Qualifier формируются с помошью идетификатора графа навигации. 

```kt
val Int.navQualifier: Qualifier
    get() = named("FlowQualifier (NavGraphId@$this)")

fun navQualifier(navGraphId: Int) =
    navGraphId.navQualifier

val Int.navScopeId: ScopeID
    get() = "ScopeId (NavGraphId@${this})"


```
