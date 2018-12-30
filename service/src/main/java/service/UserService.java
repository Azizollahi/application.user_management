package service;

import domain.User;
import infrastructure.repository.InfoRepository;
import infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.exceptions.UserNotFoundException;
import service.interfaces.IUserServices;

@Service
public class UserService implements IUserServices {
	private UserRepository repository;
	private InfoRepository infoRepository;
	@Autowired
	public UserService(UserRepository repository, InfoRepository infoRepository){
		this.repository = repository;
		this.infoRepository = infoRepository;
	}
	@Override
	public User findUserById(Long id) throws UserNotFoundException {
		var user = repository.findById(id);
		if(user.isPresent())
			return user.get();
		throw new UserNotFoundException("user by id " + id + " not found!");
	}

	@Override
	public void saveUser(User user) {
		infoRepository.save(user.getPersonalInformation());
		repository.save(user);
	}
}
