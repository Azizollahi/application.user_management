package service.auth;

import framework.LocalDateTimeUtility;
import domain.Account;
import domain.TokenInformation;
import domain.exception.InvalidTokenException;
import infrastructure.repository.TokenStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.auth.interfaces.IAuthorizationService;
import service.exceptions.AccountIsLockException;
import service.exceptions.TokenGenerationException;
import service.interfaces.ITokenService;
import java.time.LocalDateTime;

@Service
public class AuthorizationService implements IAuthorizationService {
	private Account account;
	private TokenStoreRepository tokenStoreRepository;
	private ITokenService tokenService;

	@Autowired
	public AuthorizationService(TokenStoreRepository tokenStoreRepository,
								ITokenService tokenService){
		this.tokenStoreRepository = tokenStoreRepository;
		this.tokenService = tokenService;
	}

	@Override
	public String grantAToken(Account account) throws TokenGenerationException, InvalidTokenException {
		var authorizedAccount = createTokenObject(account.getName(), account.getPassword());
		var token = tokenService.jamTokenInformation(authorizedAccount, account.getSecret());
		updateTokenStartTime(account, authorizedAccount);
		account.getToken().setToken(token);
		tokenStoreRepository.save(account.getToken());
		return token;
	}
	@Override
	public String refreshToken(TokenInformation token) throws TokenGenerationException, InvalidTokenException{
		return grantAToken(account);
	}
	@Override
	public boolean isAuthorized(TokenInformation token, String resourceName) throws AccountIsLockException {
		if(account.isLock())
			throw new AccountIsLockException("Account " + account.getName() + "is lock.");
		return account.isOpen() && !account.isLock() &&
				account.getPassword().equals(token.getAccountPassword()) &&
				LocalDateTime.now().compareTo(account.getToken().getExpirationDateTime()) > 0 &&
				account
						.getToken()
						.getStartDateTime()
						.compareTo(
								LocalDateTimeUtility
								.fromTotalSecondsToLocalDateTime(
										token.getAuthorizedDateTime()
								)
						) == 0;
	}

	@Override
	public IAuthorizationService byUsingAccount(Account account) {
		this.account = account;
		return this;
	}

	private void updateTokenStartTime(Account account, TokenInformation tokenInfo){
		account.getToken().setStartDateTime(
				LocalDateTimeUtility.fromTotalSecondsToLocalDateTime(tokenInfo.getAuthorizedDateTime()));
	}
	private TokenInformation createTokenObject(String accountName, String password){
		var authorizedAccount = new TokenInformation();
		authorizedAccount.setAccountName(accountName);
		authorizedAccount.setAccountPassword(password);
		authorizedAccount.setAuthorizedDateTime(LocalDateTimeUtility.fromLocalDateTimeToTotalSeconds(LocalDateTime.now()));
		return authorizedAccount;
	}
}
