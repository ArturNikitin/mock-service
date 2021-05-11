package com.chip.mockdemo.domain

data class User(
	val id: Long,
	val name: String,
	val age: Int,
	var address: Address? = null
)