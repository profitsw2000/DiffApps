package profitsw2000.diffapps.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import profitsw2000.diffapps.data.local.FakeRepositoryImpl
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.presentation.viewmodel.MainViewModel

val appModule = module {
    single<Repository>(named("fake_repo")) { FakeRepositoryImpl() }
    single { MainViewModel(get(named("fake_repo")), get()) }
}