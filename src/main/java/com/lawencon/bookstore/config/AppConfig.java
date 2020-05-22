package com.lawencon.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lawencon.bookstore.services.BookService;
import com.lawencon.bookstore.services.BookServiceImpl;

@Configuration
public class AppConfig {

	@Bean(name = "bookService")
	public BookService bookService() {
		return new BookServiceImpl();
	}
}
