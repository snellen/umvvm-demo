package ch.silvannellen.githubbrowser.viewmodel.user.di

import ch.silvannellen.githubbrowser.usecase.loadrepositories.di.LoadRepositoriesUseCaseModule
import ch.silvannellen.githubbrowser.usecase.loaduser.di.LoadUserUseCaseModule
import ch.silvannellen.githubbrowser.usecase.sortrepositories.di.SortRepositoriesUseCaseModule
import dagger.Module

@Module(includes = [LoadUserUseCaseModule::class, LoadRepositoriesUseCaseModule::class, SortRepositoriesUseCaseModule::class])
interface UserViewModelModule {
}