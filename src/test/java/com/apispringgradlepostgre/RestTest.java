/**
 * 
 */
package com.apispringgradlepostgre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.apispringgradlepostgre.model.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author eloi
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestTest {

	private static String URL_TEST = "http://localhost:8080/ApiSpringGradlePostgre/v1/users";

	private static long idUser;

	@SuppressWarnings("static-access")
	@Test
	public void a_postTest() {
		// User to save
		User user = new User();
		user.setName("Teste JUnit");
		user.setDateBirth(new Date());
		user.setEmail("testeJunitSpringPost@test.com");
		user.setActive(true);

		// Execute call
		RestTemplate rest = new TestRestTemplate();
		@SuppressWarnings("unchecked")
		ResponseEntity<User> response = rest.postForEntity(URL_TEST, user, User.class, Collections.EMPTY_MAP);

		// User created and returned in response
		User userCreated = response.getBody();

		// Test of JUnit...
		assertEquals(HttpStatus.CREATED, response.getStatusCode().CREATED);
		assertEquals(user.getName(), userCreated.getName());
		assertTrue(userCreated.getId() > 0);

		// Set global atribute
		idUser = userCreated.getId();

		// Dataset print in console
		System.out.println(userCreated.toString());
	}

	@SuppressWarnings("static-access")
	@Test
	public void b_getTest() {
		List<User> users = new ArrayList<User>();
		ObjectMapper mapper = new ObjectMapper();

		// Execute call
		RestTemplate rest = new TestRestTemplate();
		@SuppressWarnings("unchecked")
		ResponseEntity<String> response = rest.getForEntity(URL_TEST, String.class, Collections.EMPTY_MAP);

		// Parse string to list object
		try {
			users = mapper.readValue(response.getBody().toString(), new TypeReference<List<User>>() {
			});
		} catch (IOException e) {
			fail(e.getMessage());
		}

		// Test of JUnit...
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode().ACCEPTED);

		// Dataset print in console
		if (users.size() > 0 && !users.isEmpty()) {
			for (User user : users) {
				System.out.println(user.toString());
			}
		}
	}

	@SuppressWarnings("static-access")
	@Test
	public void c_putTest() {
		// User to update
		User user = new User();
		user.setId(idUser);
		user.setName("Teste JUnit Update");
		user.setDateBirth(new Date());
		user.setEmail("testeJunitSpringPut@test.com");
		user.setActive(true);

		// Execute call
		RestTemplate rest = new TestRestTemplate();
		try {
			rest.put(URL_TEST, user);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// RestTemplate put is void...
		@SuppressWarnings("unchecked")
		ResponseEntity<User> response = rest.getForEntity(URL_TEST + "/" + idUser, User.class, Collections.EMPTY_MAP);

		// User created and returned in response
		User userUpdated = response.getBody();

		// Test of JUnit...
		assertEquals(HttpStatus.OK, response.getStatusCode().OK);
		assertEquals(user.getName(), userUpdated.getName());
		assertTrue(userUpdated.getId() > 0);

		// Dataset print in console
		System.out.println(userUpdated.toString());
	}

	@Test
	public void d_deleteTest() {
		// Execute call
		RestTemplate rest = new TestRestTemplate();
		try {
			rest.delete(URL_TEST + "/" + idUser);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// RestTemplate delete is void...
		@SuppressWarnings("unchecked")
		ResponseEntity<User> response = rest.getForEntity(URL_TEST + "/" + idUser, User.class, Collections.EMPTY_MAP);

		// User created and returned in response
		User userFind = response.getBody();

		// Test of JUnit...
		assertEquals(null, userFind);

		// Dataset print in console
		System.out.println(userFind);
	}
}
