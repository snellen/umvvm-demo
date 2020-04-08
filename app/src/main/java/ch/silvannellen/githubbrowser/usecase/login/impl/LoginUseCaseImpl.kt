package ch.silvannellen.githubbrowser.usecase.login.impl

import ch.silvannellen.githubbrowser.model.common.AuthorizationTokenProvider
import ch.silvannellen.githubbrowser.model.github.GithubRepository
import ch.silvannellen.githubbrowser.model.github.api.di.GithubTokenProvider
import ch.silvannellen.githubbrowser.usecase.login.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
    @GithubTokenProvider private val authorizationTokenProvider: AuthorizationTokenProvider
) : LoginUseCase {

    override suspend fun execute(input: LoginUseCase.Credentials): Boolean = true

}