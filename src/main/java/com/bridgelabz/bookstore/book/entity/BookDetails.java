package com.bridgelabz.bookstore.book.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstore.book.dto.BookDetailsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_items")
public class BookDetails implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="book_Id")
	private long id;
	private String bookName;
	private String bookAuthor;
	private String bookDescription;
	private Double bookPrice;
	private int quantity;
	private String bookImage;
	private double bookRating;
	private double priceWithoutDiscount;
	
	public BookDetails(BookDetailsDTO dto) 
	{
		this.id = id;
		this.bookName = dto.getBookName();
		this.bookAuthor = dto.getBookAuthor();
		this.bookDescription = dto.getBookDescription();
		this.bookPrice = dto.getBookPrice();
		this.quantity = dto.getQuantity();
		this.bookImage = dto.getBookImage();
		this.bookRating = dto.getBookRating();
		this.priceWithoutDiscount = dto.getPriceWithoutDiscount();
	}

}
