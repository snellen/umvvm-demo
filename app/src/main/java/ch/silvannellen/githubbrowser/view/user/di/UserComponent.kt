package ch.silvannellen.githubbrowser.view.user.di

import ch.silvannellen.githubbrowser.di.GithubBrowserApplicationComponent
import ch.silvannellen.githubbrowser.di.annotation.PerFragment
import ch.silvannellen.githubbrowser.view.user.UserFragment
import ch.silvannellen.githubbrowser.viewmodel.user.UserViewModel
import ch.silvannellen.githubbrowser.viewmodel.user.di.UserViewModelModule
import dagger.Component

@PerFragment
@Component(
    dependencies = [GithubBrowserApplicationComponent::class],
    modules = [UserViewModelModule::class]
)
interface UserComponent {

    fun inject(f: UserFragment)

    fun getViewModel(): UserViewModel

}