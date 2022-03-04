package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.model.Cart;

public interface ICartService {

	public Cart insertCart(CartDTO cartdto);

	public List<Cart> getAllCartRecords();

	public Cart getCartRecord(Integer id);

	public Cart updateCartRecord(Integer id,CartDTO dto);

	public Cart deleteCartRecord(Integer id);

	public Cart updateQuantity(Integer id, Integer quantity);

}
