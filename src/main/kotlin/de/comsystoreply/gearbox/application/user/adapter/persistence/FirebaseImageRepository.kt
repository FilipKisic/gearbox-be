package de.comsystoreply.gearbox.application.user.adapter.persistence

import de.comsystoreply.gearbox.domain.user.port.persistance.ImageRepository
import org.springframework.stereotype.Repository
import org.springframework.web.multipart.MultipartFile

@Repository
class FirebaseImageRepository : ImageRepository {
    override fun save(file: MultipartFile): String {
        TODO("Not yet implemented")
    }
}