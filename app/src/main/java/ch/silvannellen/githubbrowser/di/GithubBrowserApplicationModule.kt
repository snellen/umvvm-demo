package ch.silvannellen.githubbrowser.di

import android.content.Context
import ch.silvannellen.githubbrowser.di.annotation.ApplicationContext
import ch.silvannellen.githubbrowser.model.common.AuthorizationTokenProvider
import ch.silvannellen.githubbrowser.model.common.impl.AuthorizationTokenProviderImpl
import ch.silvannellen.githubbrowser.model.github.GithubRepository
import ch.silvannellen.githubbrowser.model.github.api.ApiVersion
import ch.silvannellen.githubbrowser.model.github.api.Protocol
import ch.silvannellen.githubbrowser.model.github.api.RetrofitGithubRepository
import ch.silvannellen.githubbrowser.model.github.api.di.GithubTokenProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GithubBrowserApplicationModule(private val applicationContext: Context) {

    private val protocol = Protocol.Https
    private val apiEndpoint = "api.github.com"
    private val apiVersion = ApiVersion.v3

    @Provides
    @ApplicationContext
    fun provideApplicationContext() = applicationContext

    @Provides
    @Singleton
    fun provideGithubRepository(@GithubTokenProvider authorizationTokenProvider: AuthorizationTokenProvider): GithubRepository =
        RetrofitGithubRepository(
            protocol,
            apiEndpoint,
            apiVersion,
            authorizationTokenProvider
        )

    @Provides
    @GithubTokenProvider
    @Singleton
    fun provideAuthorizationTokenProvider(authorizationTokenProviderImpl: AuthorizationTokenProviderImpl): AuthorizationTokenProvider =
        authorizationTokenProviderImpl
}