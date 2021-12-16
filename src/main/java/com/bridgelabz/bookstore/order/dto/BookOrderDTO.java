package com.bridgelabz.bookstore.order.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class BookOrderDTO 
{
	public LocalDate orderDate;
	@PositiveOrZero
	public int quantity;
	public double price;
	public String address;
	
	
}
