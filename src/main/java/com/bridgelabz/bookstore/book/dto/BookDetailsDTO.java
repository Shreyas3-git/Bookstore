package com.bridgelabz.bookstore.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsDTO 
{
	public String bookName;
	public String bookAuthor;
	public String bookDescription;
	public double bookPrice;
	public int quantity;
	public String bookImage;
	public double bookRating;
	public double priceWithoutDiscount;

}
