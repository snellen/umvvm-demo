package ch.silvannellen.githubbrowser.usecase.loaduser.di

import ch.silvannellen.githubbrowser.usecase.loaduser.LoadUserUseCase
import ch.silvannellen.githubbrowser.usecase.loaduser.impl.LoadUserUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class LoadUserUseCaseModule {
    @Provides
    fun provideUseCase(u: LoadUserUseCaseImpl): LoadUserUseCase = u
}