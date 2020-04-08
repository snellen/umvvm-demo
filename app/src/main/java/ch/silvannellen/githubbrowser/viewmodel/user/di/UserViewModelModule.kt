package ch.silvannellen.githubbrowser.viewmodel.user.di

import ch.silvannellen.githubbrowser.usecase.loadrepositories.di.LoadRepositoriesUseCaseModule
import ch.silvannellen.githubbrowser.usecase.loaduser.di.LoadUserUseCaseModule
import dagger.Module

@Module(includes = [LoadUserUseCaseModule::class, LoadRepositoriesUseCaseModule::class])
interface UserViewModelModule {
}