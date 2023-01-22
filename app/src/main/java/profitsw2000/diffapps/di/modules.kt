package profitsw2000.diffapps.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import profitsw2000.diffapps.data.local.FakeRepositoryImpl
import profitsw2000.diffapps.data.web.KinoPoiskApi
import profitsw2000.diffapps.data.web.RepositoryImpl
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.mappers.DocsMapper
import profitsw2000.diffapps.mappers.PosterMapper
import profitsw2000.diffapps.mappers.RatingMapper
import profitsw2000.diffapps.mappers.TopFilmsMapper
import profitsw2000.diffapps.presentation.viewmodel.MainViewModel
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<String>(named(URL)) { "https://api.kinopoisk.dev/" }
    single<KinoPoiskApi> { get<Retrofit>().create(KinoPoiskApi::class.java) }
    single<Repository>(named(NAME_REMOTE)) { RepositoryImpl(get()) }
    single { Retrofit.Builder()
        .baseUrl(get<String>(named(URL)))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(get())
        .client(get())
        .build()
    }
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }

    single { OkHttpClient.Builder()
        .addInterceptor(get<HttpLoggingInterceptor>())
        .build()
    }

    single<Repository>(named(NAME_LOCAL)) { FakeRepositoryImpl() }
    single { MainViewModel(get(named(NAME_REMOTE)), get()) }
    factory<Converter.Factory> { GsonConverterFactory.create() }
    factory { PosterMapper() }
    factory { RatingMapper() }
    factory { DocsMapper(get(), get()) }
    factory { TopFilmsMapper(get()) }
}