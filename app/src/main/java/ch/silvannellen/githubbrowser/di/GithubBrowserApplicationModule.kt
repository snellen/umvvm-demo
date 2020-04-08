package ch.silvannellen.githubbrowser.di

import android.content.Context
import ch.silvannellen.githubbrowser.di.annotation.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class GithubBrowserApplicationModule(private val applicationContext: Context) {

    @Provides
    @ApplicationContext
    fun provideApplicationContext() = applicationContext

}