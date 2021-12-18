package com.bridgelabz.bookstore.user.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookUserDetailsDTO 
{
//	@NotNull(message = "user name should not be null")	
	public String fullName;
//	@NotNull(message = "user address null exception")	
	public String address;
//	@FutureOrPresent(message="register date should be future date or present date only exception")
	public LocalDate registerDate ;
//	@FutureOrPresent(message="updated date should be future date or present date only exception")
	public LocalDate updatedDate ;
//	@Email(message = "user emailId does not matches pattern exception")
	public String emailId;
//	@NotNull(message = "Password should not be null exception")
	public String password;

	public String mobile;
}
