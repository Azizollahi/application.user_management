package service.auth;

import domain.Account;
import infrastructure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.auth.interfaces.IAuthenticationService;
import service.exceptions.NotAuthorizedException;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class AuthenticationService implements IAuthenticationService {
	private AccountRepository accountRepository;
	
	@Autowired
	public AuthenticationService(AccountRepository accountRepository){
		this.accountRepository = accountRepository;
	}

	@Override
	public Account login(String userName, String password) throws NotAuthorizedException, AccountNotFoundException {
		var account = accountRepository.findAccountByNameAndOpenIsTrue(userName.toLowerCase());
		if(account == null)
			throw new AccountNotFoundException("Account " + userName + "not found");
		if(!account.getPassword().equals(password))
			throw new NotAuthorizedException("Username or password is invalid");
		return account;
	}
	@Override
	public void logout(Account account) {
		// ignored yet
	}
}
