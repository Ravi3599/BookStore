package com.bridgelabz.bookstore.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

	@Query(value="select * from wishlist where wishlist_id =:id",nativeQuery = true)
	public List<Wishlist> findByWishlistId(Integer id);

	@Query(value="select * from wishlist where bookid =:bookId",nativeQuery = true)
	public List<Wishlist> findByBookId(Integer bookId);

	@Query(value="select * from wishlist where userid =:userId",nativeQuery = true)
	public List<Wishlist> findByUserId(Integer userId);

}
