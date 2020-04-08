package ch.silvannellen.githubbrowser.usecase.loaduser.impl

import ch.silvannellen.githubbrowser.model.github.GithubRepository
import ch.silvannellen.githubbrowser.usecase.loaduser.LoadUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadUserUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository
) : LoadUserUseCase {

    override suspend fun execute(input: String): LoadUserUseCase.Result =
        withContext(Dispatchers.IO) {
            val result = githubRepository.getUser(input)
            return@withContext if (result.isSuccess()) {
                result.domainObject?.let { LoadUserUseCase.Result.Success(it) }
                    ?: LoadUserUseCase.Result.Error
            } else {
                LoadUserUseCase.Result.Error
            }
        }
}