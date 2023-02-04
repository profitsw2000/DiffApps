package profitsw2000.diffapps.repository

import androidx.paging.DataSource
import io.reactivex.rxjava3.disposables.CompositeDisposable
import profitsw2000.diffapps.data.web.RedditApi
import profitsw2000.diffapps.mappers.RedditPostsMapper
import profitsw2000.diffapps.model.Post

class RedditPostsDataSourceFactory(
    private val redditApi: RedditApi,
    private val compositeDisposable: CompositeDisposable,
    private val redditPostsMapper: RedditPostsMapper
) : DataSource.Factory<String, Post>() {

    override fun create(): DataSource<String, Post> {
        return PostDataSource(redditApi, compositeDisposable, redditPostsMapper)
    }
}