package profitsw2000.diffapps.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import profitsw2000.diffapps.data.web.RedditApi
import profitsw2000.diffapps.mappers.RedditPostsMapper
import profitsw2000.diffapps.model.AppState
import profitsw2000.diffapps.model.Post
import profitsw2000.diffapps.utils.PAGE_SIZE

class PostDataSource(
    private val redditApi: RedditApi,
    private val compositeDisposable: CompositeDisposable,
    private val redditPostsMapper: RedditPostsMapper
) : PageKeyedDataSource<String, Post>() {

    val appState: MutableLiveData<AppState> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Post>
    ) {
        appState.postValue(AppState.Loading)
        compositeDisposable.add(
            redditApi.getRedditPosts("", PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(redditPostsMapper.map(it).data.children.map { it.post }, null, redditPostsMapper.map(it).data.after)
                        appState.postValue(AppState.Loaded)
                    },
                    {
                        AppState.Error(it.toString())
                    })
        )
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Post>) {
        appState.postValue(AppState.Loading)

        compositeDisposable.add(
            redditApi.getRedditPosts(params.key, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(redditPostsMapper.map(it).data.children.map { it.post }, redditPostsMapper.map(it).data.after)
                        appState.postValue(AppState.Loaded)
                    },
                    {
                        AppState.Error(it.toString())
                    })
        )
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Post>) {

    }
}