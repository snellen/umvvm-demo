package ch.silvannellen.githubbrowser.usecase.loadcommits.di

import ch.silvannellen.githubbrowser.usecase.loadcommits.LoadCommitsUseCase
import ch.silvannellen.githubbrowser.usecase.loadcommits.impl.LoadCommitsUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class LoadCommitsUseCaseModule {
    @Provides
    fun provideUseCase(u: LoadCommitsUseCaseImpl): LoadCommitsUseCase = u
}