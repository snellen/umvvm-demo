package ch.silvannellen.githubbrowser.usecase.login

import ch.silvannellen.githubbrowser.model.common.AuthorizationTokenProvider
import ch.silvannellen.githubbrowser.model.common.Error
import ch.silvannellen.githubbrowser.model.common.Result
import ch.silvannellen.githubbrowser.model.github.GithubRepository
import ch.silvannellen.githubbrowser.model.github.User
import ch.silvannellen.githubbrowser.usecase.login.impl.LoginUseCaseImpl
import ch.silvannellen.umvvm.test.common.TestBase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.never
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.mockito.Mockito.`when` as whenever

class LoginUseCaseImplTest : TestBase() {

    lateinit var testee: LoginUseCase

    val mockGithubRepository = Mockito.mock(GithubRepository::class.java)

    val mockAuthorizationTokenProvider = Mockito.mock(AuthorizationTokenProvider::class.java)

    @Before
    fun setup() {
        testee = LoginUseCaseImpl(mockGithubRepository, mockAuthorizationTokenProvider)
    }

    @Test
    fun `given login succeeds, login use case returns null and sets the access token in the AuthorizationTokenProvider`() = runBlocking {
        // Given
        val username = "asdasd"
        val accessToken = "fgj566"
        val mockResult = Result(User("adasad", "asda2123123312"))
        whenever(
            mockGithubRepository.getUser(
                username,
                accessToken
            )
        ).thenReturn(mockResult)

        // When
        val result = testee.execute(LoginUseCase.Credentials(username, accessToken))

        // Then
        assertTrue(result)
        Mockito.verify(mockAuthorizationTokenProvider).token = accessToken
    }



    @Test
    fun `given login fails, login use case returns null and sets the access token in the AuthorizationTokenProvider`() = runBlocking {
        // Given
        val username = "asdasd"
        val accessToken = "fgj566"
        val mockResult = Result<User>(null, Error.GenericError)
        whenever(
            mockGithubRepository.getUser(
                username,
                accessToken
            )
        ).thenReturn(mockResult)

        // When
        val result = testee.execute(LoginUseCase.Credentials(username, accessToken))

        // Then
        assertFalse(result)
        Mockito.verify(mockAuthorizationTokenProvider, never()).token = accessToken
    }
}