package com.chip.mockdemo.service

import com.chip.mockdemo.domain.Address

interface UserService {
	fun getAddress(id: Long) : Address?
}