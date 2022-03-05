package com.bridgelabz.bookstore.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstore.model.Cart;

//Ability to provide CRUD operations and create table for given entity 
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
