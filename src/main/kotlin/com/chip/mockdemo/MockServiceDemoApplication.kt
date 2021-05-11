package com.chip.mockdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MockServiceDemoApplication

fun main(args: Array<String>) {
	runApplication<MockServiceDemoApplication>(*args)
}
