package service.interfaces;

import domain.Permission;

public interface IPermissionService {
	Permission findByName(String name);
}
