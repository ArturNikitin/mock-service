package com.chip.mockdemo

import com.chip.mockdemo.domain.User
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockserver.integration.ClientAndServer
import org.mockserver.matchers.Times
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [MockServiceDemoApplication::class])
class MockServiceDemoApplicationTests {
	
	companion object {
		lateinit var mockServer: ClientAndServer

		@BeforeAll
		@JvmStatic
		fun beforeAll() {
			mockServer = ClientAndServer.startClientAndServer(8081)
		}

		@AfterAll
		@JvmStatic
		fun afterAll() {
			mockServer.stop()
		}
	}
	
	@LocalServerPort
	private var port: Int = 0
	
	private val userGetUrl = "http://localhost:$port/user"

	@Test
	fun contextLoads() {
	}
	
	@Test
	fun testString() {
		val restTemplate = TestRestTemplate()
		val testUrl = "http://localhost:$port/user/test"
		val exchange = restTemplate.getForEntity(testUrl, User::class.java)
		Assertions.assertNotNull(exchange.body)
		println("$port")
		println(exchange.body)
	}
	
	@Test
	fun getUser() {
		mockServer.`when`(
			HttpRequest.request()
				.withMethod("GET")
				.withPath("/address/1"),
			Times.exactly(1)
		).respond(
			HttpResponse.response()
				.withStatusCode(200)
				.withBody(
					"{\n" +
						"    \"street\": \"First Avenue\",\n" +
						"    \"number\": 22\n" +
						"}").withHeaders(
					Header("Content-Type", "application/json")
					)
		)

		val restTemplate = TestRestTemplate()

		val exchange = restTemplate.getForEntity("http://localhost:$port/user", User::class.java)

		Assertions.assertEquals(HttpStatus.OK, exchange.statusCode)
		println(exchange.body)
	}

}
