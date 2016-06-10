package com.apispringgradlepostgre.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apispringgradlepostgre.model.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
