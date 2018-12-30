package infrastructure.repository;

import domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	Account findAccountByNameAndOpenIsTrue(String name);
	Account findAccountByName(String name);
	List<Account> findAccountsByNameContaining(String name);
	void deleteAccountById(Long id);
}
