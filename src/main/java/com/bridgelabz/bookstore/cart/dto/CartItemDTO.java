package com.bridgelabz.bookstore.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO 
{
	public String bookName;
	public String bookAuthor;
	public String bookDescirption;
	public double bookPrice;
	public int quantity;
	public String bookImage;
	public double priceWithoutDiscount;

}
