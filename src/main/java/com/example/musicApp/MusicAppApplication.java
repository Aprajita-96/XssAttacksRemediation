package com.example.musicApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication

public class MusicAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicAppApplication.class, args);
	}

}
