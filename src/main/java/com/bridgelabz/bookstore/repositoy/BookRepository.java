package com.bridgelabz.bookstore.repositoy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query(value="select * from book where bookname =:bookName",nativeQuery=true)
	public Optional<Book> findByBookName(String bookName);

	@Query(value="select * from book ORDER BY bookName ASC",nativeQuery = true)
	public List<Book> sortBooksAsc();
	
	@Query(value="select * from book ORDER BY bookName DESC",nativeQuery = true)
	public List<Book> sortBooksDesc();


}
