package ch.silvannellen.githubbrowser.usecase.loadrepositories.di

import ch.silvannellen.githubbrowser.usecase.loadrepositories.LoadRepositoriesUseCase
import ch.silvannellen.githubbrowser.usecase.loadrepositories.impl.LoadRepositoriesUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class LoadRepositoriesUseCaseModule {
    @Provides
    fun provideUseCase(u: LoadRepositoriesUseCaseImpl): LoadRepositoriesUseCase = u
}