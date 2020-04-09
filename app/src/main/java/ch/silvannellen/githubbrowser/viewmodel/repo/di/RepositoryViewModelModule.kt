package ch.silvannellen.githubbrowser.viewmodel.repo.di

import ch.silvannellen.githubbrowser.usecase.loadcommits.di.LoadCommitsUseCaseModule
import ch.silvannellen.githubbrowser.usecase.searchcommits.di.SearchCommitsUseCaseModule
import dagger.Module

@Module(includes = [LoadCommitsUseCaseModule::class, SearchCommitsUseCaseModule::class])
interface RepositoryViewModelModule {
}