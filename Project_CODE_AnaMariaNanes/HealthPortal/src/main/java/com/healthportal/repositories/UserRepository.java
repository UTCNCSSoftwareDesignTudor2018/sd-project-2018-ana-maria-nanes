package com.healthportal.repositories;

import com.healthportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(int userId);
	User findByUsername(String username);
	List<User> findAll();
	void delete(User user);
	User save(User user);

}
