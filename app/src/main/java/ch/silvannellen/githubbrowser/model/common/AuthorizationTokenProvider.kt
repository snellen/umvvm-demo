package ch.silvannellen.githubbrowser.model.common

interface AuthorizationTokenProvider {
    var token: String?
    fun resetToken()
}