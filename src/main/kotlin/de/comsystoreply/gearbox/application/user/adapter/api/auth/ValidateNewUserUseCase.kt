package de.comsystoreply.gearbox.application.user.adapter.api.auth

import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import org.springframework.stereotype.Component

@Component
class ValidateNewUserUseCase(private val userApiFacade: UserApiFacade) {
    fun execute(request: AuthenticationRequestDto) = userApiFacade.validateNewUser(request.toDomain())
}