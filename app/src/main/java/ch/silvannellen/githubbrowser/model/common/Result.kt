package ch.silvannellen.githubbrowser.model.common

/**
 * A generic result returned by a repository.
 */
data class Result<T>(
    val domainObject: T? = null,
    val error: Error? = null
) {
    fun isSuccess(): Boolean = (error == null)
}