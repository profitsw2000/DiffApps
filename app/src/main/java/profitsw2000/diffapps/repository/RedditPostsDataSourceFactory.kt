package profitsw2000.diffapps.repository

import androidx.lifecycle.MutableLiveData
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

    val postLiveDataSource = MutableLiveData<PostDataSource>()

    override fun create(): DataSource<String, Post> {
        val postDataSource = PostDataSource(redditApi, compositeDisposable, redditPostsMapper)

        postLiveDataSource.postValue(postDataSource)
        return postDataSource
    }
}