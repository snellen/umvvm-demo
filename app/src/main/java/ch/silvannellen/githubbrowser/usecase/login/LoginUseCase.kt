package ch.silvannellen.githubbrowser.usecase.login

import ch.silvannellen.umvvm.usecase.UseCase

interface LoginUseCase : UseCase<LoginUseCase.Credentials, Boolean> {
    data class Credentials(val id: String, val accessToken: String)
}