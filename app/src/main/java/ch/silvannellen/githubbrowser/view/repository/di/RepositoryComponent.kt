package ch.silvannellen.githubbrowser.view.repository.di

import ch.silvannellen.githubbrowser.di.GithubBrowserApplicationComponent
import ch.silvannellen.githubbrowser.di.annotation.PerFragment
import ch.silvannellen.githubbrowser.view.repository.RepositoryFragment
import ch.silvannellen.githubbrowser.viewmodel.repo.RepositoryViewModel
import ch.silvannellen.githubbrowser.viewmodel.repo.di.RepositoryViewModelModule
import dagger.Component

@PerFragment
@Component(
    dependencies = [GithubBrowserApplicationComponent::class],
    modules = [RepositoryViewModelModule::class]
)
interface RepositoryComponent {

    fun inject(f: RepositoryFragment)

    fun getViewModel(): RepositoryViewModel

}