package application.management.models;

import domain.Account;

public class AccountInfo {
	private Long id;
	private String name;
	private String password;
	private boolean isLock;
	private boolean isOpen;
	private String secret;
	private Long userId;

	public AccountInfo(){

	}

	public AccountInfo(Account account){
		name = account.getName();
		password = account.getPassword();
		isLock = account.isLock();
		isOpen = account.isOpen();
		secret = account.getSecret();
		id = account.getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLock() {
		return isLock;
	}

	public void setLock(boolean lock) {
		isLock = lock;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean open) {
		isOpen = open;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
