package profitsw2000.diffapps.data.web

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.rxjava3.disposables.CompositeDisposable
import profitsw2000.diffapps.domain.RepositoryWeb
import profitsw2000.diffapps.mappers.RedditPostsMapper
import profitsw2000.diffapps.model.Post
import profitsw2000.diffapps.repository.RedditPostsDataSourceFactory
import profitsw2000.diffapps.utils.PAGE_SIZE

class RepositoryWebImpl(private val redditApi: RedditApi) : RepositoryWeb {

    private lateinit var postPagedList: LiveData<PagedList<Post>>
    private lateinit var redditPostsDataSourceFactory: RedditPostsDataSourceFactory

    override fun getRedditPostsPagedList(compositeDisposable: CompositeDisposable, redditPostsMapper: RedditPostsMapper) : LiveData<PagedList<Post>> {
        redditPostsDataSourceFactory = RedditPostsDataSourceFactory(redditApi, compositeDisposable, redditPostsMapper)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()

        postPagedList = LivePagedListBuilder(redditPostsDataSourceFactory, config).build()

        return postPagedList
    }
}