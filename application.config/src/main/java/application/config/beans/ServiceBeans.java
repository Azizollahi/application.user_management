package application.config.beans;

import infrastructure.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import service.AccountService;
import service.interfaces.IAccountService;

@Configuration
public class ServiceBeans {
	private AccountRepository accountRepository;
	public ServiceBeans(AccountRepository repository){
		accountRepository = repository;
	}
	@Bean
	@SessionScope
	public IAccountService createAccountService(){
		return new AccountService(accountRepository);
	}
}
