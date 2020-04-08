package ch.silvannellen.githubbrowser.usecase.loadrepositories.impl

import ch.silvannellen.githubbrowser.model.github.GithubRepository
import ch.silvannellen.githubbrowser.usecase.loadrepositories.LoadRepositoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadRepositoriesUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository
) : LoadRepositoriesUseCase {

    override suspend fun execute(input: String): LoadRepositoriesUseCase.Result =
        withContext(Dispatchers.IO) {
            val result = githubRepository.getRepositories(input)
            return@withContext if (result.isSuccess()) {
                result.domainObject?.let { LoadRepositoriesUseCase.Result.Success(it) }
                    ?: LoadRepositoriesUseCase.Result.Error
            } else {
                LoadRepositoriesUseCase.Result.Error
            }
        }
}