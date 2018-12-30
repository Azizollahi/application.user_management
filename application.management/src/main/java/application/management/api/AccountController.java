package application.management.api;

import application.management.models.AccountInfo;
import domain.Account;
import domain.TokenInformation;
import domain.TokenStore;
import domain.exception.*;
import framework.LocalDateTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.exceptions.TokenGenerationException;
import service.exceptions.UserNotFoundException;
import service.interfaces.IAccountService;
import service.interfaces.ITokenService;
import service.interfaces.IUserServices;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
public class AccountController {

	private IAccountService accountService;
	private IUserServices userServices;
	private ITokenService tokenService;

	@Autowired
	public AccountController(IAccountService accountService, IUserServices userServices, ITokenService tokenService){
		this.accountService = accountService;
		this.userServices = userServices;
		this.tokenService = tokenService;
	}

	@DeleteMapping(value = "/api/account/delete/{id}")
	public String deleteAccount(@PathVariable(value = "id") Long accountId) throws AccountNotFoundException {
		accountService.deleteAccountById(accountId);
		return "Ok";
	}
	@GetMapping(value = "/api/account/find")
	public AccountInfo findAccount(@RequestParam(value = "accountName") String accountName) {
		var account = accountService.findAccountByName(accountName);
		return new AccountInfo(account);
	}
	@PostMapping(value = "/api/account/edit")
	@ResponseBody
	public String editAccount(@RequestBody AccountInfo request) throws
			InvalidSecretKeyException, InvalidAccountNameException,
			InvalidPasswordException, UserNotFoundException, InvalidUserException {
		var account = accountService.findAccountByName(request.getName());
		account.setSecret(request.getSecret());
		account.setName(request.getName());
		account.setPassword(request.getPassword());
		account.setLock(request.isLock());
		account.setOpen(request.isOpen());
		if(!account.getUser().getId().equals(request.getUserId())){
			var user = userServices.findUserById(request.getUserId());
			account.setUser(user);
		}
		account.update(account);
		accountService.saveAccount(account);
		return "Ok";
	}
	@PostMapping(value = "/api/account/add")
	public String addAccount(@RequestBody AccountInfo request) throws UserNotFoundException, InvalidSecretKeyException,
			InvalidTokenException, TokenGenerationException {
		var user = userServices.findUserById(request.getUserId());
		var account = new Account(user, request.getName(), request.getPassword());
		account.setSecret(request.getSecret());
		account.setLock(request.isLock());
		account.setOpen(request.isOpen());
		account.setCreationDateTime(LocalDateTime.now());
		var tokenInfo = new TokenInformation();
		tokenInfo.setAccountName(account.getName());
		tokenInfo.setAccountPassword(account.getPassword());
		tokenInfo.setAuthorizedDateTime(LocalDateTimeUtility.fromLocalDateTimeToTotalSeconds(LocalDateTime.now()));
		var finalToken = tokenService.jamTokenInformation(tokenInfo, account.getSecret());
		var tokenStore = new TokenStore(finalToken, LocalTime.of(0,20));
		tokenService.saveTokenStore(tokenStore);
		account.setToken(tokenStore);
		accountService.saveAccount(account);
		return "Ok";
	}
}
