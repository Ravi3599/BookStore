package com.bridgelabz.bookstore.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstore.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
