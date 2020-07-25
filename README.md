# Koin scope with navigation component

В этом примере я попробовал привязать Koin scope к графам навигации из Jetpack Navigation Component

### Что из себя представляет приложение
Приложение со списком фильмов, которые отсортированые по странам

В приложении есть 3 экрана
* CountriesFragment - выбор страны фильмов
* CountryMoviesFragment - список фильмов для страны
* MovieFragment - описание фильма

### Скоупы
Скоупы привязаны к графам навигации. ScopeID и Qualifier формируются с помошью идетификатора графа навигации. 

```kt
val Int.navQualifier: Qualifier
    get() = named("FlowQualifier (NavGraphId@$this)")

fun navQualifier(navGraphId: Int) =
    navGraphId.navQualifier

val Int.navScopeId: ScopeID
    get() = "ScopeId (NavGraphId@${this})"
```
