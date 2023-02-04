package profitsw2000.diffapps.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import profitsw2000.diffapps.data.web.RedditApi
import profitsw2000.diffapps.mappers.RedditPostsMapper
import profitsw2000.diffapps.model.Post
import profitsw2000.diffapps.utils.PAGE_SIZE

class PostDataSource(
    private val redditApi: RedditApi,
    private val compositeDisposable: CompositeDisposable,
    private val redditPostsMapper: RedditPostsMapper
) : PageKeyedDataSource<String, Post>() {
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Post>) {
        compositeDisposable.add(
            redditApi.getRedditPosts(params.key, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(redditPostsMapper.map(it).data.children.map { it.post }, redditPostsMapper.map(it).data.after)
                    },
                    {
                        it.message?.let { it1 -> Log.d("QueryError", it1) }
                    })
        )
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Post>) {

    }

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Post>
    ) {
        compositeDisposable.add(
            redditApi.getRedditPosts("", PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(redditPostsMapper.map(it).data.children.map { it.post }, null, redditPostsMapper.map(it).data.after)
                    },
                    {
                        it.message?.let { it1 -> Log.d("QueryError", it1) }
                    })
        )
    }
}