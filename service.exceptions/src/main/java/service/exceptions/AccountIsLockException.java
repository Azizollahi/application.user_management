package service.exceptions;

public class AccountIsLockException extends Exception {
	public AccountIsLockException(String message) {
		super(message);
	}
}
