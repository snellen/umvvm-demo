package ch.silvannellen.githubbrowser.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.silvannellen.githubbrowser.usecase.login.LoginUseCase
import ch.silvannellen.githubbrowser.viewmodel.common.Event
import ch.silvannellen.umvvm.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    private val _loginAvailable = MutableLiveData<Boolean>().apply { value = false }
    val loginAvailable: LiveData<Boolean> = _loginAvailable

    private val _busy = MutableLiveData<Boolean>().apply { value = false }
    val busy: LiveData<Boolean> = _busy

    private val _loginError = MutableLiveData<Boolean>().apply { value = false }
    val loginError: LiveData<Boolean> = _loginError

    private val _navigateToWelcomeFragment = MutableLiveData<Event<String>>()
    val navigateToWelcomeFragment: LiveData<Event<String>> = _navigateToWelcomeFragment

    fun onCredentialsChanged(username: String, accessToken: String) {
        _loginAvailable.value = username.isNotEmpty() && accessToken.isNotEmpty()
    }

    fun onLoginRequested(userName: String, accessToken: String) {
        launch {
            _busy.value = true
            _loginError.value = false
            val success =
                loginUseCase.execute(LoginUseCase.Credentials(userName, accessToken))
            if (success) {
                _navigateToWelcomeFragment.value = Event(userName)
            } else {
                _loginError.value = true
            }
            _busy.value = false
        }
    }

}