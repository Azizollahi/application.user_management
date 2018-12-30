package infrastructure.repository;

import domain.management.ViewManagementTableHeader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewManagementTableHeaderRepository extends CrudRepository<ViewManagementTableHeader, Long> {
	List<ViewManagementTableHeader> findViewManagementTableHeaderByManagementNameOrderByIdAsc(String managementName);

}
