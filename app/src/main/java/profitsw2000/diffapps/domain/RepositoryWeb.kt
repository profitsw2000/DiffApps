package profitsw2000.diffapps.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import io.reactivex.rxjava3.disposables.CompositeDisposable
import profitsw2000.diffapps.mappers.RedditPostsMapper
import profitsw2000.diffapps.model.Post

interface RepositoryWeb: Repository {

    fun getRedditPostsPagedList(
        compositeDisposable: CompositeDisposable,
        redditPostsMapper: RedditPostsMapper
    ): LiveData<PagedList<Post>>

}