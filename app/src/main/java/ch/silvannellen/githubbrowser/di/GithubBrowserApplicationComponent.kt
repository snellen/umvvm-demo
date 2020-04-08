package ch.silvannellen.githubbrowser.di

import android.content.Context
import ch.silvannellen.githubbrowser.di.annotation.ApplicationContext
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

}