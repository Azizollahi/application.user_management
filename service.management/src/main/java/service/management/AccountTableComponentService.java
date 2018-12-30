package service.management;

import infrastructure.repository.ViewManagementTableHeaderRepository;
import org.springframework.stereotype.Service;
import service.management.interfaces.ITableComponentService;
import java.util.LinkedList;
import java.util.List;

@Service
public class AccountTableComponentService implements ITableComponentService {

	private ViewManagementTableHeaderRepository viewManagementTableHeaderRepository;
	public AccountTableComponentService(ViewManagementTableHeaderRepository viewManagementTableHeaderRepository){
		this.viewManagementTableHeaderRepository = viewManagementTableHeaderRepository;
	}
	@Override
	public List<String> findAllHeaders(String name) {
		var viewManagementTableHeader = viewManagementTableHeaderRepository.findViewManagementTableHeaderByManagementNameOrderByIdAsc(name);
		var headerNames = new LinkedList<String>();
		for (var viewHeaders: viewManagementTableHeader
		) {
			headerNames.add(viewHeaders.getHeaderName());
		}
		return headerNames;
	}

}
