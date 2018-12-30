package domain;

public class TokenInformation {
	private String accountName;
	private String accountPassword;
	private long authorizedDateTime;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public long getAuthorizedDateTime() {
		return authorizedDateTime;
	}

	public void setAuthorizedDateTime(long authorizedDateTime) {
		this.authorizedDateTime = authorizedDateTime;
	}
}
