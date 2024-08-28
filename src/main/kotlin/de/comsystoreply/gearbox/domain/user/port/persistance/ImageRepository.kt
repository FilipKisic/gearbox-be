package de.comsystoreply.gearbox.domain.user.port.persistance

import org.springframework.web.multipart.MultipartFile

/**
 * Basic Image repository which manages images in the datasource
 */
interface ImageRepository {
    fun save(file: MultipartFile): String
}