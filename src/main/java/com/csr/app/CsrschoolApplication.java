package com.csr.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan("com.csr")
public class CsrschoolApplication {
	public static void main(String[] args) {
		SpringApplication.run(CsrschoolApplication.class, args);
	}
}
