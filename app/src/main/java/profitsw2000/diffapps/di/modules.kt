package profitsw2000.diffapps.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import profitsw2000.diffapps.data.web.RedditApi
import profitsw2000.diffapps.data.web.RepositoryWebImpl
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.mappers.ChildrenMapper
import profitsw2000.diffapps.mappers.DataMapper
import profitsw2000.diffapps.mappers.PostMapper
import profitsw2000.diffapps.mappers.RedditPostsMapper
import profitsw2000.diffapps.presentation.viewmodel.MainViewModel
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<String>(named(URL)) { "https://www.reddit.com/r/aww/" }
    single<RedditApi> { get<Retrofit>().create(RedditApi::class.java) }
    single<Repository>(named(NAME_REMOTE)) { RepositoryWebImpl(get()) }
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
    single { MainViewModel(get(named(NAME_REMOTE)), get()) }

    factory<Converter.Factory> { GsonConverterFactory.create() }
    factory { PostMapper() }
    factory { ChildrenMapper(get()) }
    factory { DataMapper(get()) }
    factory { RedditPostsMapper(get()) }

}