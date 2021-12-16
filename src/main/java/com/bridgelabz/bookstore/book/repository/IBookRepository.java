package com.bridgelabz.bookstore.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.book.entity.BookDetails;

@Repository
public interface IBookRepository extends JpaRepository<BookDetails,Integer>
{
	public Optional<BookDetails> findByBookName(String name);
	
}
