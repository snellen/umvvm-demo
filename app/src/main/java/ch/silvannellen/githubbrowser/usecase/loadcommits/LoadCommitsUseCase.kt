package ch.silvannellen.githubbrowser.usecase.loadcommits

import ch.silvannellen.githubbrowser.model.github.Commit
import ch.silvannellen.umvvm.usecase.UseCase

interface LoadCommitsUseCase : UseCase<LoadCommitsUseCase.Input, LoadCommitsUseCase.Result> {
    data class Input(val owner: String, val repo: String)

    sealed class Result {
        data class Success(val commits: Collection<Commit>) : Result()
        object Error : Result()
    }
}