package ch.silvannellen.githubbrowser.model.common.impl

import ch.silvannellen.githubbrowser.model.common.AuthorizationTokenProvider
import javax.inject.Inject

class AuthorizationTokenProviderImpl @Inject constructor(): AuthorizationTokenProvider {
    override var token: String? = null

    override fun resetToken() {
        token = null
    }
}