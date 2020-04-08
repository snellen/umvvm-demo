package ch.silvannellen.githubbrowser.viewmodel.repo.di

import ch.silvannellen.githubbrowser.usecase.loadcommits.di.LoadCommitsUseCaseModule
import dagger.Module

@Module(includes = [LoadCommitsUseCaseModule::class])
interface RepositoryViewModelModule {
}