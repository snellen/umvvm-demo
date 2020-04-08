package ch.silvannellen.githubbrowser.viewmodel.login

import ch.silvannellen.githubbrowser.usecase.login.LoginUseCase
import ch.silvannellen.umvvm.test.viewmodel.ViewModelTestBase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.mockito.Mockito.`when` as whenever

class LoginViewModelTest : ViewModelTestBase() {

    lateinit var testee: LoginViewModel

    private val mockLoginUseCase = Mockito.mock(LoginUseCase::class.java)

    @Before
    fun setup() {
        testee = LoginViewModel(mockLoginUseCase)
    }

    @Test
    fun `given view model is created, live data is initialized`() {
        // Given

        // When

        // Then
        assertEquals(false, testee.loginAvailable.value)
        assertEquals(false, testee.busy.value)
        assertEquals(false, testee.loginError.value)
        assertNull(testee.navigateToWelcomeFragment.value?.peekContent())
    }

    @Test
    fun `given login available, when credentials are empty then set login unavailable`() {
        // Given
        val username = ""
        val accessToken = ""
        testee.onCredentialsChanged("username", "accessToken")
        assertEquals(true, testee.loginAvailable.value)

        // When
        testee.onCredentialsChanged(username, accessToken)

        // Then
        assertEquals(false, testee.loginAvailable.value)
    }

    @Test
    fun `given login unavailable, when credentials are empty then set login unavailable`() {
        // Given
        val username = "asdasd"
        val accessToken = "fgj566"
        assertEquals(false, testee.loginAvailable.value)

        // When
        testee.onCredentialsChanged(username, accessToken)

        // Then
        assertEquals(true, testee.loginAvailable.value)
    }

    @Test
    fun `when login requested and login succeeds then navigate to welcome fragment with the given username`() {
        // Given
        val username = "asdasd"
        val accessToken = "fgj566"
        runBlockingTest {  whenever(mockLoginUseCase.execute(LoginUseCase.Credentials(username, accessToken))).thenReturn(true) }

        // When
        testee.onLoginRequested(username, accessToken)

        // Then
        assertEquals(username, testee.navigateToWelcomeFragment.value?.peekContent())
        assertEquals(false, testee.loginError.value)
        assertEquals(false, testee.busy.value)
    }

    @Test
    fun `when login requested and login fails then don't navigate to welcome fragment`() {
        // Given
        val username = "asdasd"
        val accessToken = "fgj566"
        runBlockingTest {  whenever(mockLoginUseCase.execute(LoginUseCase.Credentials(username, accessToken))).thenReturn(false) }

        // When
        testee.onLoginRequested(username, accessToken)

        // Then
        assertNull(testee.navigateToWelcomeFragment.value?.peekContent())
        assertEquals(true, testee.loginError.value)
        assertEquals(false, testee.busy.value)
    }

    @Test
    fun `when login is in progress then busy is true`() {
        // Given
        val username = "asdasd"
        val accessToken = "fgj566"
        val loginUseCase = object : LoginUseCase {
            var wasBusy: Boolean? = null
            override suspend fun execute(input: LoginUseCase.Credentials): Boolean {
                wasBusy = testee.busy.value
                return true
            }

        }
        testee = LoginViewModel(loginUseCase)

        // When
        testee.onLoginRequested(username, accessToken)

        // Then
        assertEquals(username, testee.navigateToWelcomeFragment.value?.peekContent())
        assertEquals(false, testee.loginError.value)
        assertEquals(false, testee.busy.value)
        assertEquals(true, loginUseCase.wasBusy)
    }

}