package com.bridgelabz.bookstore.repositoy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.model.Book;

//Ability to provide CRUD operations and create table for given entity 
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query(value="select * from book where book_name LIKE :bookName%",nativeQuery=true)
	public List<Book> findByBookName(String bookName);

	@Query(value="select * from book ORDER BY price",nativeQuery = true)
	public List<Book> sortBooksAsc();
	
	@Query(value="select * from book ORDER BY price DESC",nativeQuery = true)
	public List<Book> sortBooksDesc();

	@Query(value="select * from book where bookid =:id",nativeQuery=true)
	public List<Book> findByBookId(Integer id);


}
