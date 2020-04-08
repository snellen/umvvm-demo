package ch.silvannellen.githubbrowser.viewmodel.user.di

import ch.silvannellen.githubbrowser.usecase.loaduser.di.LoadUserUseCaseModule
import dagger.Module

@Module(includes = [LoadUserUseCaseModule::class])
interface UserViewModelModule {
}