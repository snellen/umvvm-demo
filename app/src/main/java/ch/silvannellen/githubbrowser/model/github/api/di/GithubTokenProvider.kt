package ch.silvannellen.githubbrowser.model.github.api.di

import javax.inject.Qualifier

/**
 * Qualifier to request an injected AuthorizationTokenProvider to be the one used by the GitHub repository
 */
@Qualifier
annotation class GithubTokenProvider {
}