package profitsw2000.diffapps.model

import TopFilms

sealed class AppState {
    data class Success(val topFilms: TopFilms) : AppState()
    data class Error(val message: String) : AppState()
    object Loading : AppState()
}
