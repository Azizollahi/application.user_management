package service.auth.interfaces;

import domain.Account;
import domain.TokenInformation;
import domain.exception.InvalidTokenException;
import service.exceptions.AccountIsLockException;
import service.exceptions.TokenGenerationException;

import java.io.IOException;

public interface IAuthorizationService {
	String grantAToken(Account account) throws TokenGenerationException, InvalidTokenException;

	String refreshToken(TokenInformation token) throws IOException, TokenGenerationException, InvalidTokenException;

	boolean isAuthorized(TokenInformation token, String resourceName) throws IOException, AccountIsLockException;
	IAuthorizationService byUsingAccount(Account account);
}
