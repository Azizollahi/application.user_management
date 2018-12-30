package service.auth.interfaces;

import domain.exception.InvalidTokenException;
import service.exceptions.AccountIsLockException;
import service.exceptions.NotAuthorizedException;
import service.exceptions.TokenGenerationException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public interface IAuthFacadeService {
	String authorize(String token, String resourceName, String accountName) throws IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IOException, NotAuthorizedException, TokenGenerationException, InvalidTokenException, AccountIsLockException;
	String authenticate(String userName, String password) throws NotAuthorizedException, TokenGenerationException, InvalidTokenException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, AccountNotFoundException;
}
