package profitsw2000.diffapps.di

import org.koin.dsl.module
import profitsw2000.diffapps.data.RepositoryImpl
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.viewmodel.MainViewModel

val appModule = module {
    single<Repository> { RepositoryImpl() }
    single { MainViewModel(get()) }
}