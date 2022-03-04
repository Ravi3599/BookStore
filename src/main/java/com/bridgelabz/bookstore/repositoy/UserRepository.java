package com.bridgelabz.bookstore.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value="select * from user_details where email =:mail",nativeQuery = true)
	public Optional<User> findByMail(String mail);

}
