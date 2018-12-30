package infrastructure.repository;

import domain.management.ViewManagementHeader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderInfoRepository extends CrudRepository<ViewManagementHeader, Long> {
	ViewManagementHeader findByName(String name);
}
