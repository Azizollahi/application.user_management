package application.config.repository;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"infrastructure.repository"})
@EntityScan(basePackages = {"domain"})
public class RepositoryConfig {

}
