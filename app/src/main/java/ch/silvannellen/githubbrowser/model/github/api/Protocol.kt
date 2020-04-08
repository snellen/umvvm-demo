package ch.silvannellen.githubbrowser.model.github.api

sealed class Protocol(val protocolString: String) {
    object Http : Protocol("http")
    object Https : Protocol("https")
}