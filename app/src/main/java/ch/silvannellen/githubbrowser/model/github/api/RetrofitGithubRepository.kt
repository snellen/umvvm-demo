package ch.silvannellen.githubbrowser.model.github.api

import ch.silvannellen.githubbrowser.model.common.AuthorizationTokenProvider
import ch.silvannellen.githubbrowser.model.common.Result
import ch.silvannellen.githubbrowser.model.common.retrofit.RetrofitExecutor
import ch.silvannellen.githubbrowser.model.github.CodeRepository
import ch.silvannellen.githubbrowser.model.github.GithubRepository
import ch.silvannellen.githubbrowser.model.github.User
import ch.silvannellen.githubbrowser.model.github.api.v3.AUTHORIZATION_HEADER_FIELD
import ch.silvannellen.githubbrowser.model.github.api.v3.GithubApi
import ch.silvannellen.githubbrowser.model.github.api.v3.VERSION_HEADER_FIELD
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class RetrofitGithubRepository(
    private val httpProtocol: Protocol,
    private val apiEndpoint: String,
    private val apiVersion: ApiVersion,
    private val authorizationTokenProvider: AuthorizationTokenProvider
) : GithubRepository {

    private val headerInterceptor: (Interceptor.Chain) -> Response = { chain ->
        val builder = chain.request().newBuilder()
        builder.addHeader(
            VERSION_HEADER_FIELD,
            "application/vnd.github.${apiVersion.versionString}+json"
        )
        authorizationTokenProvider.token?.let {
            if (chain.request().headers.get(AUTHORIZATION_HEADER_FIELD) == null) {
                builder.addHeader(AUTHORIZATION_HEADER_FIELD, generateAuthHeader(it))
            }
        }
        chain.proceed(builder.build())
    }
    private val loggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) = Timber.d(message)
        }).apply { level = HttpLoggingInterceptor.Level.BODY }

    private val githubApiV3: GithubApi by lazy {
        Retrofit.Builder()
            .baseUrl("${httpProtocol.protocolString}://$apiEndpoint/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .build()
            .create(GithubApi::class.java)
    }

    override fun getUser(name: String, accessToken: String?): Result<User> {
        return RetrofitExecutor.execute<ch.silvannellen.githubbrowser.model.github.api.v3.dto.User, User>(
            { apiForCurrentVersion.getUser(name, accessToken?.let { generateAuthHeader(it) }) },
            { Result(User(it.login, it.avatarUrl)) })
    }

    override fun getRepositories(userName: String): Result<Collection<CodeRepository>> {
        return RetrofitExecutor.execute<Collection<ch.silvannellen.githubbrowser.model.github.api.v3.dto.Repository>, Collection<CodeRepository>>(
            { apiForCurrentVersion.getRepositories(userName) },
            { Result(it.map { CodeRepository(it.id, it.name, it.language, it.updatedAt) }) })
    }

    private val apiForCurrentVersion: GithubApi
        get() =
            when (apiVersion) {
                is ApiVersion.v3 -> githubApiV3
            }

    private fun generateAuthHeader(token: String) = "token $token"

}