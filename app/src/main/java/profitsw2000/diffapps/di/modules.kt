package profitsw2000.diffapps.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import profitsw2000.diffapps.data.local.FakeRepositoryImpl
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.mappers.DocsMapper
import profitsw2000.diffapps.mappers.PosterMapper
import profitsw2000.diffapps.mappers.RatingMapper
import profitsw2000.diffapps.mappers.TopFilmsMapper
import profitsw2000.diffapps.presentation.viewmodel.MainViewModel

val appModule = module {
    factory { PosterMapper() }
    factory { RatingMapper() }
    factory { DocsMapper(get(), get()) }
    factory { TopFilmsMapper(get()) }
    single<Repository>(named("fake_repo")) { FakeRepositoryImpl() }
    single { MainViewModel(get(named("fake_repo")), get()) }
}