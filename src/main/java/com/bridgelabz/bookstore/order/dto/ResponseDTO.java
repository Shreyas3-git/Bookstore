package com.bridgelabz.bookstore.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ResponseDTO 
{
	public String message;
	public Object data;
	
	public ResponseDTO(String message,Object data)
	{
		this.message = message;
		this.data = data;
	}
}
