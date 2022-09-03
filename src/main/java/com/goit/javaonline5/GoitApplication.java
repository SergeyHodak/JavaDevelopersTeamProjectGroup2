package com.goit.javaonline5;

import goit.connection.DbInitService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoitApplication {

	public static void main(String[] args) {
		new DbInitService().initDb();
	}

}
