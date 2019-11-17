package com.tdg.mur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MurApplication {

	public static void main(String[] args) {
		SpringApplication.run(MurApplication.class, args);
	}

}
