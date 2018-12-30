package domain.management;

public class DashboardUserInfo {
	private Long accountId;
	private String userFirstName;
	private String userLastName;
	private String email;
	private String accountPicUrl;
	private Boolean isLock;
	private Boolean isOpen;
	private String creationDateTime;
	private String lastInvalidLoginTime;
	private String lastLogin;
	private String accountName;

	public DashboardUserInfo(String userFirstName, String userLastName, String accountPicUrl, String accountName, String email) {
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.accountPicUrl = accountPicUrl;
		this.accountName = accountName;
		this.email = email;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getAccountPicUrl() {
		return accountPicUrl;
	}

	public void setAccountPicUrl(String accountPicUrl) {
		this.accountPicUrl = accountPicUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Boolean getLock() {
		return isLock;
	}

	public void setLock(Boolean lock) {
		isLock = lock;
	}

	public Boolean getOpen() {
		return isOpen;
	}

	public void setOpen(Boolean open) {
		isOpen = open;
	}

	public String getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getLastInvalidLoginTime() {
		return lastInvalidLoginTime;
	}

	public void setLastInvalidLoginTime(String lastInvalidLoginTime) {
		this.lastInvalidLoginTime = lastInvalidLoginTime;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
