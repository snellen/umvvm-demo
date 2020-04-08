package ch.silvannellen.githubbrowser.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.silvannellen.githubbrowser.model.github.CodeRepository
import ch.silvannellen.githubbrowser.usecase.loaduser.LoadUserUseCase
import ch.silvannellen.githubbrowser.viewmodel.common.Event
import ch.silvannellen.umvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val loadUserUseCase: LoadUserUseCase
) : BaseViewModel() {

    private val _userName: MutableLiveData<String> = MutableLiveData()
    val userName: LiveData<String> = _userName

    private val _userAvatarUrl: MutableLiveData<String> = MutableLiveData()
    val userAvatarUrl: LiveData<String> = _userAvatarUrl

    private val _repositories: MutableLiveData<Collection<CodeRepository>> = MutableLiveData()
    val repositories: LiveData<Collection<CodeRepository>> = _repositories

    private val _refreshingRepositoriesList = MutableLiveData<Boolean>().apply { value = false }
    val refreshingRepositoryList: LiveData<Boolean> = _refreshingRepositoriesList

    private val _loadingProfile = MutableLiveData<Boolean>().apply { value = false }
    val loadingProfile: LiveData<Boolean> = _loadingProfile

    private val _loadUserError = MutableLiveData<Boolean>().apply { value = false }
    val loadUserError: LiveData<Boolean> = _loadUserError

    private val _loadRepositoriesError = MutableLiveData<Boolean>().apply { value = false }
    val loadRepositoriesError: LiveData<Boolean> = _loadRepositoriesError

    data class CodeRepositoryNavigationSpec(val owner: String, val repoName: String)

    private val _navigateToRepository = MutableLiveData<Event<CodeRepositoryNavigationSpec>>()
    val navigateToRepository: LiveData<Event<CodeRepositoryNavigationSpec>> = _navigateToRepository

    fun loadProfile(userName: String) {
        _loadingProfile.value = true
        _loadUserError.value = false
        _loadRepositoriesError.value = false
        _repositories.value = listOf()
        launch {
            coroutineScope {
                launch { onLoadUserResult(loadUserUseCase.execute(userName)) }
            }
            _loadingProfile.value = false
        }
    }

    private fun onLoadUserResult(userResult: LoadUserUseCase.Result) {
        _loadUserError.value = userResult !is LoadUserUseCase.Result.Success
        if (userResult is LoadUserUseCase.Result.Success) {
            _userName.value = userResult.user.login
            _userAvatarUrl.value = userResult.user.avatarUrl
        }
    }

}