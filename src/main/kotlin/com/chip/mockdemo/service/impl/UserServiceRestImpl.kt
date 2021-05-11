package com.chip.mockdemo.service.impl

import com.chip.mockdemo.domain.Address
import com.chip.mockdemo.service.UserService
import org.mockserver.integration.ClientAndServer
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service


@Service
@Primary
class UserServiceRestImpl(
	val restTemplateBuilder: RestTemplateBuilder
) : UserService {
	private val url = "http://localhost:8081/address/"
	private val mockServer: ClientAndServer? = null
	
	override fun getAddress(id: Long): Address? {
		val restTemplate = restTemplateBuilder.build()
		return restTemplate.getForEntity(url + id, Address::class.java).body
				?: throw RuntimeException("Address for User with $id not found")

	}
}