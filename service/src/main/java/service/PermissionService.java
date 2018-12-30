package service;

import domain.Permission;
import infrastructure.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.interfaces.IPermissionService;

@Service
public class PermissionService implements IPermissionService {
	private PermissionRepository repository;

	@Autowired
	public PermissionService(PermissionRepository permissionRepository){
		repository = permissionRepository;
	}

	@Override
	public Permission findByName(String name) {
		return repository.findPermissionByName(name);
	}
}
