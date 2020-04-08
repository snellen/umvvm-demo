package ch.silvannellen.githubbrowser.usecase.login.di

import ch.silvannellen.githubbrowser.usecase.login.LoginUseCase
import ch.silvannellen.githubbrowser.usecase.login.impl.LoginUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class LoginUseCaseModule {
    @Provides
    fun provideUseCase(u: LoginUseCaseImpl): LoginUseCase = u
}