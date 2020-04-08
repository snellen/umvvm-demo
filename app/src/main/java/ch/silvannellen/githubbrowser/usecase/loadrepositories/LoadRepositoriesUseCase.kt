package ch.silvannellen.githubbrowser.usecase.loadrepositories

import ch.silvannellen.githubbrowser.model.github.CodeRepository
import ch.silvannellen.umvvm.usecase.UseCase

interface LoadRepositoriesUseCase : UseCase<String, LoadRepositoriesUseCase.Result> {
    sealed class Result {
        data class Success(val repos: Collection<CodeRepository>) : Result()
        object Error : Result()
    }
}