package spring.data.pattern.integration.reliable.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlakyApiApp
{
	public static void main(String[] args) {
		SpringApplication.run(FlakyApiApp.class, args);
	}
}
