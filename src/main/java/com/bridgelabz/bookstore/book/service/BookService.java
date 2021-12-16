package com.bridgelabz.bookstore.book.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.bookstore.book.dto.BookDetailsDTO;
import com.bridgelabz.bookstore.book.entity.BookDetails;
import com.bridgelabz.bookstore.book.repository.IBookRepository;
import com.bridgelabz.bookstore.book.tokenutils.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService implements IBookService
{
	@Autowired
	private IBookRepository bookRepository;
	
	@Autowired
	private TokenUtil tokenUtils;
	
	@Autowired
	private RestTemplate restTemplate;
	
	

	@Override
	public List<BookDetails> retriveAllBooks(String emailId) //throws Exception
	{

		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if(!isuserPresent)
		{
			log.info("Exception occure user not found:");
//			return new Exception("User not found Exception:");
			return null;
		}
			List<BookDetails> bookEntity = bookRepository.findAll();
			return bookEntity;
	}

	@Override
	public BookDetails getBookByName(String name,String emailId) 
	{
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (!isuserPresent) 
		{
			return null;
		} 
		BookDetails getOneBook = bookRepository.findByBookName(name).orElse(null);
		return getOneBook;

	}

	@Override
	public List<BookDetails> getBookhigherTolowerPresidence(String emailId) 
	{
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (!isuserPresent) {
			return null;
		}
		
		List<BookDetails> bookDetails = bookRepository.findAll();

		Collections.sort(bookDetails, new Comparator<BookDetails>() {

			@Override
			public int compare(BookDetails o1, BookDetails o2) {
				return o2.getBookPrice().compareTo(o1.getBookPrice());
			}

		});

		
		return bookDetails;
	}
	

	@Override
	public List<BookDetails> getBooklowerTohigherPresidence(String emailId) 
	{
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (!isuserPresent) {
			return null;
		}
		

		List<BookDetails> bookDetails = bookRepository.findAll();
		
		Collections.sort(bookDetails, new Comparator<BookDetails>() {

			@Override
			public int compare(BookDetails o1, BookDetails o2) {
				return o1.getBookPrice().compareTo(o2.getBookPrice());
			}

		});

		return bookDetails;

	}

	@Override
	public BookDetails insertBook(String emailId,BookDetailsDTO dto) //throws Exception
	{
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (isUserPresent) 
		{
			Optional<BookDetails> searchBookByName = bookRepository.findByBookName(dto.getBookName());
			if (!searchBookByName.isPresent()) 
			{
				BookDetails book = new BookDetails(dto);
				bookRepository.save(book);
				return book;
			}
			log.error("Book is already present is Bookstore:");
			return null;
		} 
		log.error("user already exist try with different email:");
		return null;
	}

	@Override
	public Optional<BookDetails> updateBook(String emailId, String bookName,BookDetailsDTO dto) //throws Exception
	{
		
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId", boolean.class);
		if (!isuserPresent) 
		{
			log.error("user not found Exception:");
			return null;
		}
			Optional<BookDetails> bookEntity = bookRepository.findByBookName(bookName);
			if(bookEntity.isPresent())
			{
				bookEntity.get().setBookName(dto.getBookName());
				bookEntity.get().setBookAuthor(dto.getBookAuthor());
				bookEntity.get().setBookDescription(dto.getBookDescription());
				bookEntity.get().setBookImage(dto.getBookImage());
				bookEntity.get().setBookPrice(dto.getBookPrice());
				bookEntity.get().setQuantity(dto.getQuantity());
				bookEntity.get().setBookRating(dto.getBookRating());
				bookEntity.get().setPriceWithoutDiscount(dto.getPriceWithoutDiscount());
				
				restTemplate.put("http://localhost:8082/updatecart?emailId?bookName"+emailId,bookName,dto, Object.class);
				bookRepository.save(bookEntity.get());
				return bookEntity;
			}
			log.info("Book Not found Exception:");
			return null;
		
	}

	@Override
	public Optional<BookDetails> updateBookPrice(String emailId,double price,String bookName) //throws Exception
	{
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (!isuserPresent) 
		{
			log.error("user not found Exception:");
			return null;
		}
		Optional<BookDetails> getOneBook = bookRepository.findByBookName(bookName);
		if (getOneBook.isPresent()) 
		{
			getOneBook.get().setBookPrice(price);
			bookRepository.save(getOneBook.get());
			return getOneBook;
		}
		return null;
	}

	@Override
	public Optional<BookDetails> updateBookQuantity(String emailId,int quantity,String bookName) //throws Exception
	{

		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if (!isuserPresent) 
		{
			log.error("user not found Exception:");
			return null;
		}

		Optional<BookDetails> getBook = bookRepository.findByBookName(bookName);
		if (getBook.isPresent()) 
		{
			getBook.get().setQuantity(quantity);
			bookRepository.save(getBook.get());
			return getBook;
		}
		log.info("Book not found Exception:");		
		return null;
	}

	@Override
	public Optional<BookDetails> deleteBook(String emailId,String bookName) 
	{
		boolean isuserPresent = restTemplate.getForObject("http://localhost:8088/verifyuser?emailId"+emailId, boolean.class);
		if(!isuserPresent) {
			return null;
		}
		
		Optional<BookDetails> deleteBook = bookRepository.findByBookName(bookName);
		if (deleteBook.isPresent()) 
		{
			bookRepository.delete(deleteBook.get());
			return null;
		}
		log.info("book name does not exist exception");
		return null;
	}

	@Override
	public int getCountOfBooks() 
	{
		List<BookDetails> bookCount = bookRepository.findAll();
		return bookCount.size();
	}

	
}
