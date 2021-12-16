package com.bridgelabz.bookstore.book.service;

import java.util.List;
import java.util.Optional;
import com.bridgelabz.bookstore.book.dto.BookDetailsDTO;
import com.bridgelabz.bookstore.book.entity.BookDetails;

public interface IBookService 
{
	public abstract List<BookDetails> retriveAllBooks(String emailId);//thows Exception ;
	public abstract BookDetails getBookByName(String name,String emailId);
	public abstract  int getCountOfBooks();
	public abstract List<BookDetails> getBookhigherTolowerPresidence(String emailId);
	public abstract List<BookDetails> getBooklowerTohigherPresidence(String emailId);
	public abstract BookDetails insertBook(String emailId,BookDetailsDTO dto); //throws Exception;
	public abstract Optional<BookDetails> updateBook(String emailId,String bookName,BookDetailsDTO dto); //throws Exception;
	public abstract Optional<BookDetails> updateBookPrice(String emailId,double price,String bookName); //throws Exception;
	public abstract Optional<BookDetails> updateBookQuantity(String emailId,int quantity,String bookName); //throws Exception;
	public abstract Optional<BookDetails> deleteBook(String emailId,String bookName);
	
}
