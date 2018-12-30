package service;

import domain.Account;
import infrastructure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.interfaces.IAccountService;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AccountService implements IAccountService {
	private AccountRepository accountRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository){
		this.accountRepository = accountRepository;
	}

	@Override
	public Account findAccountByName(String accountName) {
		return accountRepository.findAccountByName(accountName);
	}

	@Override
	public Account findOpenAccountByName(String accountName) {
		return accountRepository.findAccountByNameAndOpenIsTrue(accountName);
	}

	@Override
	public List<Account> findAccountsLike(String accountName) {
		return accountRepository.findAccountsByNameContaining(accountName);
	}

	@Override
	public void deleteAccountById(Long accountId) throws AccountNotFoundException {
		var account = accountRepository.findById(accountId);
		if(account.isPresent()){
			account.get().setOpen(false);
			accountRepository.save(account.get());
			return;
		}
		throw new AccountNotFoundException();
	}

	@Override
	public void saveAccount(Account account){
		accountRepository.save(account);
	}
}
