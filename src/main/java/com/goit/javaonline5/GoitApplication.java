package com.goit.javaonline5;

import com.goit.javaonline5.connection.DbInitService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoitApplication {
	public static void main(String[] args) {
		new DbInitService().initDb();
		SpringApplication.run(GoitApplication.class, args);
	}

}
