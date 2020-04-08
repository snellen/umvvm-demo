package ch.silvannellen.githubbrowser.usecase.sortrepositories.impl

import ch.silvannellen.githubbrowser.model.github.CodeRepository
import ch.silvannellen.githubbrowser.usecase.sortrepositories.SortRepositoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This is an example of a use case that implements a piece of business logic, but does not fetch data.
 */
class SortRepositoriesUseCaseImpl @Inject constructor() : SortRepositoriesUseCase {

    override suspend fun execute(input: Collection<CodeRepository>): Collection<CodeRepository> =
        withContext(Dispatchers.Default) {
            input.sortedByDescending(CodeRepository::updatedAt)
        }
}