package ch.silvannellen.githubbrowser

import android.app.Application
import ch.silvannellen.githubbrowser.di.DaggerGithubBrowserApplicationComponent
import ch.silvannellen.githubbrowser.di.GithubBrowserApplicationComponent
import ch.silvannellen.githubbrowser.di.GithubBrowserApplicationModule
import timber.log.Timber

class GithubBrowserApplication : Application() {

    val applicationComponent: GithubBrowserApplicationComponent =
        DaggerGithubBrowserApplicationComponent.builder().githubBrowserApplicationModule(
            GithubBrowserApplicationModule(this)
        ).build()

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}