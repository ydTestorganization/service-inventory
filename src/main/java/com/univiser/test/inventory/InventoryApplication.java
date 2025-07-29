package com.univiser.test.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {

		ApplicationContext context =SpringApplication.run(InventoryApplication.class, args);

	}

}
