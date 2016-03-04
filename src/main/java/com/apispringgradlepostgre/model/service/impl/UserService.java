package com.apispringgradlepostgre.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apispringgradlepostgre.model.entity.User;
import com.apispringgradlepostgre.model.persistence.dao.IUserRepository;
import com.apispringgradlepostgre.model.service.IUserService;
import com.apispringgradlepostgre.model.service.common.AbstractService;

/**
 * @author eloi.bilek
 *
 */
@Service
@Transactional
public class UserService extends AbstractService<User> implements IUserService {

	private IUserRepository userRepository;

	public UserService() {
		super();
	}

	@Override
	public User findById(final long id) {
		User user = userRepository.findOne(id);
		return user;
	}

	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		users = userRepository.findAll();
		return users;
	}

	public User create(final User user) {
		return userRepository.save(user);
	}

	public User update(User user) {
		if (userRepository.exists(user.getId())) {
			user = userRepository.saveAndFlush(user);
		} else {
			user = null;
		}
		return user;
	}

	public void deleteById(final long id) {
		userRepository.delete(id);
	}

	@Autowired
	public void setDao(IUserRepository dao) {
		this.userRepository = dao;
	}

	@Override
	protected PagingAndSortingRepository<User, Long> getDao() {
		return userRepository;
	}

}
