/**
 * 
 */
package com.apispringgradlepostgre.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apispringgradlepostgre.model.entity.User;
import com.apispringgradlepostgre.model.service.IUserService;

/**
 * @author eloi.bilek
 *
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {

	@Autowired
	private IUserService userService;

	// @Autowired
	// private ApplicationEventPublisher eventPublisher;

	public UserController() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> findAll() {
		return userService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable final Long id) {
		return userService.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody final User resource, final HttpServletResponse response) {
		return userService.create(resource);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public User update(@RequestBody final User resource, final HttpServletResponse response) {
		return userService.update(resource);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable final Long id, final HttpServletResponse response) {
		userService.deleteById(id);
	}

}
