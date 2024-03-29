package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.model.Cart;

//Interface to achieve abstraction
public interface ICartService {

	public Cart insertCart(CartDTO cartdto);

	public List<Cart> getAllCartRecords();

	public Cart getCartRecord(Integer id);

	public Cart updateCartRecord(Integer id,CartDTO dto);

	public Cart deleteCartRecord(Integer id);

	public Cart updateQuantity(Integer id, Integer quantity);

	public Cart decreaseQuantity(Integer id);

	public Cart increaseQuantity(Integer id);

	public Cart getCartRecordByBookId(Integer bookID);
	
	public List<Cart> getCartRecordByUserId(Integer userID);

	public List<Cart> getCartRecordByUserAndBookId(Integer userID, Integer bookID);


}
