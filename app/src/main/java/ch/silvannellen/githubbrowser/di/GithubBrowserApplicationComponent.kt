package ch.silvannellen.githubbrowser.di

import android.content.Context
import ch.silvannellen.githubbrowser.di.annotation.ApplicationContext
import ch.silvannellen.githubbrowser.model.common.AuthorizationTokenProvider
import ch.silvannellen.githubbrowser.model.github.GithubRepository
import ch.silvannellen.githubbrowser.model.github.api.di.GithubTokenProvider
import dagger.Component
import javax.inject.Singleton

/**
 * This Dagger component provisions app wide dependencies.
 */
@Singleton
@Component(modules = [GithubBrowserApplicationModule::class])
interface GithubBrowserApplicationComponent {

    @ApplicationContext
    fun getApplicationContext(): Context

    fun getGithubRepository(): GithubRepository

    @GithubTokenProvider
    fun getAuthorizationTokenProvider(): AuthorizationTokenProvider
}