package ch.silvannellen.githubbrowser.view.login.di

import ch.silvannellen.githubbrowser.di.GithubBrowserApplicationComponent
import ch.silvannellen.githubbrowser.di.annotation.PerFragment
import ch.silvannellen.githubbrowser.view.login.LoginFragment
import ch.silvannellen.githubbrowser.viewmodel.login.LoginViewModel
import ch.silvannellen.githubbrowser.viewmodel.login.di.LoginViewModelModule
import dagger.Component

@PerFragment
@Component(
    dependencies = [GithubBrowserApplicationComponent::class],
    modules = [LoginViewModelModule::class]
)
interface LoginComponent {

    fun inject(f: LoginFragment)

    fun getViewModel(): LoginViewModel

}