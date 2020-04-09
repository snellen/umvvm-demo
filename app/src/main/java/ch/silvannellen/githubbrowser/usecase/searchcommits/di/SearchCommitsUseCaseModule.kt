package ch.silvannellen.githubbrowser.usecase.searchcommits.di

import ch.silvannellen.githubbrowser.usecase.searchcommits.SearchCommitsUseCase
import ch.silvannellen.githubbrowser.usecase.searchcommits.impl.SearchCommitsUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class SearchCommitsUseCaseModule {
    @Provides
    fun provideUseCase(u: SearchCommitsUseCaseImpl): SearchCommitsUseCase = u
}