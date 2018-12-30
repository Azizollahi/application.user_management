package service;

import domain.Role;
import infrastructure.repository.PermissionRepository;
import infrastructure.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.exceptions.RoleNotFoundException;
import service.interfaces.IRoleService;

@Service
public class RoleService implements IRoleService {
	private RoleRepository repository;
	private PermissionRepository permissionRepository;
	@Autowired
	public RoleService(RoleRepository repository, PermissionRepository permissionRepository){
		this.repository = repository;
		this.permissionRepository = permissionRepository;
	}
	public Role findRoleById(Long id) throws RoleNotFoundException {
		var role = repository.findById(id);
		if(role.isPresent())
			return role.get();
		throw new RoleNotFoundException("Role not found");

	}

	@Override
	public void saveRole(Role role) {
		for (var permission: role.getPermissions()
			 ) {
			permissionRepository.save(permission);
		}
		repository.save(role);
	}
}
