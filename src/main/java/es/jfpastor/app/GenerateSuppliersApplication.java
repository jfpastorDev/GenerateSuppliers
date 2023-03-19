package es.jfpastor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.jfpastor.app.controller.IGenerateController;

@SpringBootApplication
public class GenerateSuppliersApplication implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(GenerateSuppliersApplication.class);

	@Autowired
	IGenerateController controller;
	
	public static void main(String[] args) {
		SpringApplication.run(GenerateSuppliersApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			controller.generate(args);
		} catch (Exception e) {
		}
	}
	
	
}
