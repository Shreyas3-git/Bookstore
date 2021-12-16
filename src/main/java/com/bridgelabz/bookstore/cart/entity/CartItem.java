package com.bridgelabz.bookstore.cart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstore.cart.dto.CartItemDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cart_items")
public class CartItem 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long userId;
	private long bookId;
	private String bookName;
	private String bookAuthor;
	private String bookDescirption;
	private double bookPrice;
	private int quantity;
	private String bookImage;
	private double priceWithoutDiscount;
	
	
	
	public CartItem(CartItemDTO dto)
	{
		this.id = id;
		this.bookName = dto.getBookName();
		this.bookAuthor = dto.getBookAuthor();
		this.bookDescirption = dto.getBookDescirption();
		this.bookPrice = dto.getBookPrice();
		this.quantity = dto.getQuantity();
		this.bookImage = dto.getBookImage();
		this.priceWithoutDiscount = dto.getPriceWithoutDiscount();
	}
	
	
	
//	public CartItem(BookDetails dto)
//	{
//		this.id = id;
//		this.bookName = dto.getBookName();
//		this.bookAuthor = dto.getBookAuthor();
//		this.bookDescirption = dto.getBookDescription();
//		this.bookPrice = dto.getBookPrice();
//		this.quantity = dto.getQuantity();
//		this.bookImage = dto.getBookImage();
//		this.priceWithoutDiscount = dto.getPriceWithoutDiscount();
//	}
	
	
}
