package com.goit.javaonline5.user.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserRegistrationDto {

	private String firstName;

	private String lastName;

	@Email(message = "Email is not valid", regexp = "[A-Za-z\\d!#$%&'*+/=?^_`.{|}~-]+@[a-z\\d]+.[a-z\\d]+")
	@NotEmpty(message = "Email cannot be empty")
	private String email;

	private String password;
}
