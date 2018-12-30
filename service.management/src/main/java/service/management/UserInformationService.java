package service.management;

import domain.Account;
import domain.management.DashboardUserInfo;
import org.springframework.stereotype.Service;
import service.management.interfaces.IUserInformationService;
import java.time.format.DateTimeFormatter;

@Service
public class UserInformationService implements IUserInformationService {
	private static final String USTIMEFORMAT = "MM/dd/yyyy HH:mm:ss";
	@Override
	public DashboardUserInfo satisfyUserInfo(Account account) {
		var userInfo = new DashboardUserInfo(account.getUser().getFirstName(), account.getUser().getLastName(),
				account.getPicPath(), account.getName(), account.getUser().getEmail());
		userInfo.setOpen(account.isOpen());
		userInfo.setLock(account.isLock());
		if(account.getCreationDateTime() != null)
			userInfo.setCreationDateTime(account.getCreationDateTime().format(DateTimeFormatter.ofPattern(USTIMEFORMAT)));
		if(account.getLastLogin() != null)
			userInfo.setLastLogin(account.getLastLogin().format(DateTimeFormatter.ofPattern(USTIMEFORMAT)));
		if(account.getLastInvalidLogin() != null)
			userInfo.setLastInvalidLoginTime(account.getLastInvalidLogin().format(DateTimeFormatter.ofPattern(USTIMEFORMAT)));
		return userInfo;
	}
}
