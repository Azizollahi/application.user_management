package service.interfaces;

import domain.User;
import service.exceptions.UserNotFoundException;

public interface IUserServices {
	User findUserById(Long id) throws UserNotFoundException;
	void saveUser(User user);
}
