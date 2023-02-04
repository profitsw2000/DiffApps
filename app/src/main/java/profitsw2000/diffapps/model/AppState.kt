package profitsw2000.diffapps.model

sealed class AppState {
    data class Error(val message: String) : AppState()
    object Loading : AppState()
    object Loaded : AppState()
}
