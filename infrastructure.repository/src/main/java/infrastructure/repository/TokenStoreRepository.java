package infrastructure.repository;

import domain.TokenStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenStoreRepository extends CrudRepository<TokenStore, Long> {

}
