package com.goit.javaonline5.user.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDto {

	private String firstName;

	private String lastName;

	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
	@NotEmpty(message = "Email cannot be empty")
	@Size(min = 5, max = 50, message = "Email length should be in range 5-50 symbols")
	private String email;

	@Size(min = 8, max = 100, message = "Password length should be in range 8-100 symbols")
	@NotEmpty
	private String password;
}
