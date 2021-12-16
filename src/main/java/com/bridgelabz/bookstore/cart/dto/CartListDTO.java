package com.bridgelabz.bookstore.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartListDTO 
{
//	BookDetails bookEntity;
	int quantity;
	long id;
	long userId;
	
}
