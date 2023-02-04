package profitsw2000.diffapps.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import io.reactivex.rxjava3.disposables.CompositeDisposable
import profitsw2000.diffapps.data.web.RepositoryWebImpl
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.mappers.RedditPostsMapper
import profitsw2000.diffapps.model.AppState
import profitsw2000.diffapps.model.Post

class MainViewModel(
    private val repository: Repository,
    private val redditPostsMapper: RedditPostsMapper
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _postPagedList: LiveData<PagedList<Post>> by lazy {
        (repository as RepositoryWebImpl).getRedditPostsPagedList(compositeDisposable, redditPostsMapper)
    }
    val postPagedList: LiveData<PagedList<Post>> by this::_postPagedList

    private val _appState: LiveData<AppState> by lazy {
        (repository as RepositoryWebImpl).getAppState()
    }
    val appState: LiveData<AppState> by this::_appState

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}