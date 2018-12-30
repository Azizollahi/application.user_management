package service.interfaces;

import domain.Role;
import service.exceptions.RoleNotFoundException;

public interface IRoleService {
	Role findRoleById(Long id) throws RoleNotFoundException;
	void saveRole(Role role);
}
