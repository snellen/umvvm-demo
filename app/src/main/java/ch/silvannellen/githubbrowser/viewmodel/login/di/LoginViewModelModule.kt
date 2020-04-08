package ch.silvannellen.githubbrowser.viewmodel.login.di

import ch.silvannellen.githubbrowser.usecase.login.di.LoginUseCaseModule
import dagger.Module

@Module(includes = [LoginUseCaseModule::class])
interface LoginViewModelModule {
}