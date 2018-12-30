package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"application.config",
		"application.auth.controllers",
		"application.config.beans",
		"service.auth",
		"infrastructure.repository",
		"application.config.repository",
		"application.management.controllers",
		"application.management.api",
		"infrastructure.cryptography",
		"service"
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
