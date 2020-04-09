package ch.silvannellen.githubbrowser.model.github.api.v3

import ch.silvannellen.githubbrowser.model.github.api.v3.dto.CommitNode
import ch.silvannellen.githubbrowser.model.github.api.v3.dto.Repository
import ch.silvannellen.githubbrowser.model.github.api.v3.dto.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

const val OAUTH_SCOPES_HEADER_FIELD = "X-OAuth-Scopes"
const val AUTHORIZATION_HEADER_FIELD = "Authorization"
const val VERSION_HEADER_FIELD = "Accept"

interface GithubApi {
    @GET("/users/{login}")
    fun getUser(
        @Path("login") login: String,
        @Header(AUTHORIZATION_HEADER_FIELD) authHeader: String? = null
    ): Call<User>

    @GET("/users/{user}/repos")
    fun getRepositories(@Path("user") user: String): Call<Collection<Repository>>

    @GET("/repos/{user}/{repo}/commits")
    fun getCommits(@Path("user") user: String, @Path("repo") repo: String): Call<Collection<CommitNode>>
}