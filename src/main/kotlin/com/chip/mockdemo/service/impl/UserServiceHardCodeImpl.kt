package com.chip.mockdemo.service.impl

import com.chip.mockdemo.domain.Address
import com.chip.mockdemo.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceHardCodeImpl : UserService {
	override fun getAddress(id: Long) = Address("First Avenue", 10)
}