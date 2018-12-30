package service.auth.interfaces;

import domain.Account;
import service.exceptions.NotAuthorizedException;

import javax.security.auth.login.AccountNotFoundException;

public interface IAuthenticationService {
	Account login(String userName, String password) throws NotAuthorizedException, AccountNotFoundException;
	void logout(Account userName);
}
