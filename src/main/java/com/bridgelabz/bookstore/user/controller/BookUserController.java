package com.bridgelabz.bookstore.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.user.dto.BookUserDetailsDTO;
import com.bridgelabz.bookstore.user.dto.ForgotPasswordDTO;
import com.bridgelabz.bookstore.user.dto.LoginDTO;
import com.bridgelabz.bookstore.user.dto.ResponseDTO;
import com.bridgelabz.bookstore.user.entity.BookUserDetails;
import com.bridgelabz.bookstore.user.service.IUserService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class BookUserController 
{
	
	@Autowired
	private IUserService  bookUserServce;
	
	@GetMapping("/verifyuser")
	public boolean verifyUserDetails(@RequestParam String emailId)
	{
		boolean isuserPresent = bookUserServce.verifyUser(emailId);
		return isuserPresent;
	}

	@GetMapping("/allusers")
	public ResponseEntity<ResponseDTO> getAllUsers(@RequestHeader String Token) 
	{
		List<BookUserDetails> retriveAll = bookUserServce.findAllUsers(Token);
		ResponseDTO dto = new ResponseDTO("All Users fetch successfully:",retriveAll);
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	
	@PostMapping("/registeruser")
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody BookUserDetailsDTO signupDTO)
	{
		BookUserDetails addUser = bookUserServce.registerNewUser(signupDTO);
		ResponseDTO dto = new ResponseDTO("User registered successfully:",addUser);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@PostMapping("/userlogin")
	public ResponseEntity<ResponseDTO> userLogin(@RequestBody LoginDTO logindto)
	{
		ResponseDTO login = bookUserServce.UserLogin(logindto);
		return new ResponseEntity<>(login,HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/updateuser/{id}")
	public ResponseEntity<ResponseDTO> updateUserDetails(@RequestBody BookUserDetailsDTO userDTO,@RequestHeader String token) 
	{
		Optional<BookUserDetails> updateUser = bookUserServce.updateUser(userDTO,token);
		ResponseDTO dto = new ResponseDTO("User updated successfully:",updateUser);
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/forgotpassword")
	public ResponseEntity<ResponseDTO> forgotUserPassword(@RequestBody ForgotPasswordDTO passwordDTO) 
	{
		Optional<BookUserDetails> forgotPassword = bookUserServce.forgotPassword(passwordDTO);
		ResponseDTO dto = new ResponseDTO("password updated successfully:",forgotPassword);
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteuser")
	public ResponseEntity<ResponseDTO> deleteUser(@RequestHeader String token)
	{
		Optional<BookUserDetails> deleteUser = bookUserServce.deleteUser(token);
		ResponseDTO dto = new ResponseDTO("User deleted successfully:",deleteUser);
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
}
