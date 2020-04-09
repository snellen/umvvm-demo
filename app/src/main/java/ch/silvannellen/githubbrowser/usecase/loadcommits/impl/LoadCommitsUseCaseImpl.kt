package ch.silvannellen.githubbrowser.usecase.loadcommits.impl

import ch.silvannellen.githubbrowser.model.github.GithubRepository
import ch.silvannellen.githubbrowser.usecase.loadcommits.LoadCommitsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadCommitsUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository
) : LoadCommitsUseCase {

    override suspend fun execute(input: LoadCommitsUseCase.Input): LoadCommitsUseCase.Result =
        withContext(Dispatchers.IO) {
            val result = githubRepository.getCommits(input.owner, input.repo)
            return@withContext if (result.isSuccess()) {
                result.domainObject?.let { LoadCommitsUseCase.Result.Success(it) }
                    ?: LoadCommitsUseCase.Result.Error
            } else {
                LoadCommitsUseCase.Result.Error
            }
        }
}