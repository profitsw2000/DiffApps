package profitsw2000.diffapps.di

import org.koin.dsl.module
import profitsw2000.diffapps.presentation.viewmodel.MapViewModel

val appModule = module {
    single { MapViewModel() }
}