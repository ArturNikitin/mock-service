package com.chip.mockdemo.controller

import com.chip.mockdemo.domain.User
import com.chip.mockdemo.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
	val userService: UserService
) {
	
	@GetMapping
	fun getUser(): User {
		val user = User(1L, "Artur", 22)
		val address = userService.getAddress(user.id)
		return user.also {
			it.address = address
		}
	}
	
	@GetMapping("/test")
	fun test() = User(2L, "Test", 29)
}
