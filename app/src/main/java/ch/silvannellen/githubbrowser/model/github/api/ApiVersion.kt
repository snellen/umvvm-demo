package ch.silvannellen.githubbrowser.model.github.api

sealed class ApiVersion(val versionString: String) {
    object v3 : ApiVersion("v3")
}