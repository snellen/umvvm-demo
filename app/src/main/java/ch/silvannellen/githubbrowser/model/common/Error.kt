package ch.silvannellen.githubbrowser.model.common

/**
 * Common errors returned by repositories.
 * (In a real world application, there would be more...)
 */
sealed class Error {
    object GenericError : Error()
    object Unauthorized : Error()
}