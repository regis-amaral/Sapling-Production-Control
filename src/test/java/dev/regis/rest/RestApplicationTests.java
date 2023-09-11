package dev.regis.rest;


import org.springframework.boot.test.context.SpringBootTest;

import dev.regis.rest.models.person.Client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

@SpringBootTest
class RestApplicationTests {

	@Test
	void createClientTest() {
		Client client = new Client();
		client.setName("regis");
		String name = client.getName();
		assertEquals("regis", name);
	}

}
