package infrastructure.repository;

import domain.management.CardInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardInfoRepository extends CrudRepository<CardInfo, Long> {
	List<CardInfo> findByName(String name);
}
