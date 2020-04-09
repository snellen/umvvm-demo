package ch.silvannellen.githubbrowser.usecase.searchcommits.impl

import ch.silvannellen.githubbrowser.model.github.CodeRepository
import ch.silvannellen.githubbrowser.model.github.Commit
import ch.silvannellen.githubbrowser.usecase.searchcommits.SearchCommitsUseCase
import ch.silvannellen.githubbrowser.usecase.sortrepositories.SortRepositoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This is an example of a use case that implements a piece of business logic, but does not fetch data.
 */
class SearchCommitsUseCaseImpl @Inject constructor() : SearchCommitsUseCase {

    override suspend fun execute(input: SearchCommitsUseCase.Input): Collection<Commit> =
        withContext(Dispatchers.Default) {
            input.commits.filter { it.message.contains(input.query, true) }
        }
}