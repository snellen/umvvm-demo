package ch.silvannellen.githubbrowser.usecase.loaduser

import ch.silvannellen.githubbrowser.model.github.User
import ch.silvannellen.umvvm.usecase.UseCase

interface LoadUserUseCase : UseCase<String, LoadUserUseCase.Result> {
    sealed class Result {
        data class Success(val user: User) : Result()
        object Error : Result()
    }
}