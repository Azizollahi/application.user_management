package service.auth;

import com.google.common.io.BaseEncoding;
import domain.Account;
import domain.exception.InvalidTokenException;
import infrastructure.cryptography.interfaces.ICryptoActor;
import infrastructure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.auth.interfaces.IAuthFacadeService;
import service.auth.interfaces.IAuthenticationService;
import service.auth.interfaces.IAuthorizationService;
import service.exceptions.AccountIsLockException;
import service.exceptions.NotAuthorizedException;
import service.exceptions.TokenGenerationException;
import service.interfaces.ITokenService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

@Service
public class AuthFacadeService implements IAuthFacadeService {
	private IAuthorizationService authorizationService;
	private ITokenService tokenService;
	private ICryptoActor cryptoActor;
	private AccountRepository accountRepository;
	private IAuthenticationService authenticationService;

	@Autowired
	public AuthFacadeService(IAuthorizationService authorizationService, IAuthenticationService authenticationService,
							 ITokenService tokenService, ICryptoActor cryptoActor,
							 AccountRepository accountRepository){
		this.authorizationService = authorizationService;
		this.authenticationService = authenticationService;
		this.tokenService = tokenService;
		this.cryptoActor = cryptoActor;
		this.accountRepository = accountRepository;
	}
	@Override
	public String authorize(String token, String resourceName, String accountName) throws
			IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException,
			BadPaddingException, IOException, NotAuthorizedException, TokenGenerationException,
			InvalidTokenException, AccountIsLockException {
		var account = accountRepository.findAccountByNameAndOpenIsTrue(accountName.toLowerCase());
		var plainToken = decryptToken(token, account);
		var tokenInformation = tokenService.extractTokenInformation(plainToken, account.getSecret());
		authorizationService.byUsingAccount(account);
		if(!authorizationService.isAuthorized(tokenInformation, resourceName))
			throw new NotAuthorizedException("account is not authorized");
		var refreshToken = authorizationService.refreshToken(tokenInformation);
		return encryptToken(refreshToken, account);
	}

	@Override
	public String authenticate(String userName, String password) throws NotAuthorizedException,
			TokenGenerationException, InvalidTokenException, InvalidKeyException, BadPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, AccountNotFoundException {
		var account = authenticationService.login(userName, password);
		var token = authorizationService.grantAToken(account);
		return encryptToken(token, account);
	}

	private String encryptToken(String token, Account account) throws IllegalBlockSizeException, InvalidKeyException,
			InvalidAlgorithmParameterException, BadPaddingException {
		var plainToken = cryptoActor
				.byUsingKey(account.getSecret().getBytes())
				.encrypt(token.getBytes());
		return BaseEncoding.base32().encode(plainToken);
	}

	private String decryptToken(String token, Account account) throws IllegalBlockSizeException, InvalidKeyException,
			InvalidAlgorithmParameterException, BadPaddingException {
		var tokenBytes = BaseEncoding.base32().decode(token);
		var plainToken = cryptoActor
				.byUsingKey(account.getSecret().getBytes())
				.decrypt(tokenBytes);
		return new String(plainToken);
	}
}
