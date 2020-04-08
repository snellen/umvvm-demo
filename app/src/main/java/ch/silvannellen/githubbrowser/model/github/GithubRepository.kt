package ch.silvannellen.githubbrowser.model.github

import ch.silvannellen.githubbrowser.model.common.Result

/**
 * The repository implementation for the Github-API
 */
interface GithubRepository {

    /**
     * Get the user (optionally using the access token specified)
     */
    fun getUser(name: String, accessToken: String? = null): Result<User>

    /**
     * Get the repositories of the given user
     */
    fun getRepositories(userName: String): Result<Collection<CodeRepository>>
}