package service.management.interfaces;

import domain.Account;
import domain.management.DashboardUserInfo;

public interface IUserInformationService {
	DashboardUserInfo satisfyUserInfo(Account accountName);
}
