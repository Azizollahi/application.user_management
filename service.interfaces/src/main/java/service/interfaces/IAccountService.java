package service.interfaces;

import domain.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface IAccountService {
	Account findAccountByName(String accountName);
	Account findOpenAccountByName(String accountName);
	List<Account> findAccountsLike(String accountName);
	void deleteAccountById(Long accountId) throws AccountNotFoundException;
	void saveAccount(Account account);
}
