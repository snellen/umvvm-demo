package ch.silvannellen.githubbrowser.viewmodel.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.silvannellen.githubbrowser.model.github.Commit
import ch.silvannellen.githubbrowser.usecase.loadcommits.LoadCommitsUseCase
import ch.silvannellen.githubbrowser.usecase.searchcommits.SearchCommitsUseCase
import ch.silvannellen.umvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(
    private val loadCommitsUseCase: LoadCommitsUseCase,
    private val searchCommitsUseCase: SearchCommitsUseCase
) : BaseViewModel() {

    private lateinit var repoOwner: String
    private lateinit var repositoryName: String
    private var searchQuery: String? = null
    private var unfilteredCommits: Collection<Commit>? = null

    private val _repoName: MutableLiveData<String> = MutableLiveData()
    val repoName: LiveData<String> = _repoName

    private val _commits: MutableLiveData<Collection<Commit>> = MutableLiveData()
    val commits: LiveData<Collection<Commit>> = _commits

    private val _loadingCommits = MutableLiveData<Boolean>().apply { value = false }
    val loadingCommits: LiveData<Boolean> = _loadingCommits

    private val _refreshingCommits = MutableLiveData<Boolean>().apply { value = false }
    val refreshingCommits: LiveData<Boolean> = _refreshingCommits

    private val _loadCommitsError = MutableLiveData<Boolean>().apply { value = false }
    val loadCommitsError: LiveData<Boolean> = _loadCommitsError

    fun initialise(repoOwner: String, repoName: String) {
        this.repoOwner = repoOwner
        this.repositoryName = repoName
        _repoName.value = repoName
    }

    fun loadCommits() {
        loadCommits(_loadingCommits)

    }

    fun refreshCommitsList() {
        loadCommits(_refreshingCommits)
    }

    fun searchCommits(query: String) {
        searchQuery = if (query.isNotEmpty()) query else null
        launch { updateCommits() }
    }

    private fun loadCommits(loadingProperty: MutableLiveData<Boolean>) {
        loadingProperty.value = true
        _loadCommitsError.value = false
        _commits.value = emptyList()
        unfilteredCommits = emptyList()
        launch {
            onLoadCommitsResult(
                loadCommitsUseCase.execute(
                    LoadCommitsUseCase.Input(
                        repoOwner,
                        repositoryName
                    )
                )
            )
            loadingProperty.value = false
        }
    }

    private suspend fun onLoadCommitsResult(loadCommitsResult: LoadCommitsUseCase.Result) {
        _loadCommitsError.value = loadCommitsResult !is LoadCommitsUseCase.Result.Success
        if (loadCommitsResult is LoadCommitsUseCase.Result.Success) {
            unfilteredCommits = loadCommitsResult.commits
            updateCommits()
        }
    }

    private suspend fun updateCommits() {
        _commits.value = unfilteredCommits?.let { commits ->
            searchQuery?.let { query ->
                searchCommitsUseCase.execute(SearchCommitsUseCase.Input(query, commits))
            } ?: commits
        } ?: emptyList()
    }

}