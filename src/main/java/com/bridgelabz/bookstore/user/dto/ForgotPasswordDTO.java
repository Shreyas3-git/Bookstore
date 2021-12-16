package com.bridgelabz.bookstore.user.dto;

import lombok.Data;

@Data
public class ForgotPasswordDTO 
{
//	@NotBlank(message = "emailId can not be blank")
//	@Email(message = "Enter Valid email")
	public String emailId;
	
//	@NotBlank(message = "password can not be blank")
	public String newPassword;
	
	//	@NotBlank(message = "confirm password can not be blank")
	public String confirmPassword;

}
