package com.bridgelabz.bookstore.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO 
{
//	@NotBlank(message = "emailId can not be blank")
//	@Email(message = "Enter Valid email")
	public String emailId;
//	@NotBlank(message = "password can not be blank")
	public String password;

}
