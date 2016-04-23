package com.twinklexsh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twinklexsh.domain.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	Users findByUsername(String string);
	
	List<Users> findAllByUsername(String string);

	Users findByUsernameAndUserPassword(String username, String userPassword);

}
