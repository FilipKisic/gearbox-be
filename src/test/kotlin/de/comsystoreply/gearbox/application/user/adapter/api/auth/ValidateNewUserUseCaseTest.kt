package de.comsystoreply.gearbox.application.user.adapter.api.auth

import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.port.api.PasswordPolicyViolationException
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ValidateNewUserUseCaseTest {
    private lateinit var userApiFacade: UserApiFacade
    private lateinit var validateNewUserUseCase: ValidateNewUserUseCase

    @BeforeEach
    fun setUp() {
        userApiFacade = mockk()
        validateNewUserUseCase = ValidateNewUserUseCase(userApiFacade)
    }

    @Test
    fun `validateNewUser should pass on valid input`() {
        val request = AuthenticationRequestDto(
            email = "test@example.com",
            username = "test",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!"
        )

        every { userApiFacade.validateNewUser(any()) } returns Unit

        validateNewUserUseCase.execute(request)
        verify { userApiFacade.validateNewUser(any()) }
    }

    @Test
    fun `validateNewUser should throw error on invalid input`() {
        val request = AuthenticationRequestDto(
            email = "test@example.com",
            username = "test",
            password = "short",
            confirmPassword = "short"
        )

        every {
            userApiFacade.validateNewUser(any())
        } throws PasswordPolicyViolationException("Password must have at least eight characters.")

        val exception = assertThrows<PasswordPolicyViolationException> {
            validateNewUserUseCase.execute(request)
        }

        Assertions.assertEquals("Password must have at least eight characters.", exception.message)
    }
}