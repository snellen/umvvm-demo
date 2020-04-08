package ch.silvannellen.githubbrowser.usecase.sortrepositories.di

import ch.silvannellen.githubbrowser.usecase.sortrepositories.SortRepositoriesUseCase
import ch.silvannellen.githubbrowser.usecase.sortrepositories.impl.SortRepositoriesUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class SortRepositoriesUseCaseModule {
    @Provides
    fun provideUseCase(u: SortRepositoriesUseCaseImpl): SortRepositoriesUseCase = u
}