package ch.silvannellen.githubbrowser.usecase.searchcommits

import ch.silvannellen.githubbrowser.model.github.Commit
import ch.silvannellen.umvvm.usecase.UseCase

interface SearchCommitsUseCase : UseCase<SearchCommitsUseCase.Input, Collection<Commit>> {
    data class Input(val query: String, val commits: Collection<Commit>)
}