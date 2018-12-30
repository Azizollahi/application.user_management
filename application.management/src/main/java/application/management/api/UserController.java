package application.management.api;

import application.management.models.UserInfo;
import domain.Info;
import domain.User;
import domain.exception.InvalidPersonalInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.exceptions.UserNotFoundException;
import service.interfaces.IUserServices;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class UserController {
	private IUserServices userServices;

	@Autowired
	public UserController(IUserServices userServices){
		this.userServices = userServices;
	}

	@PostMapping("/api/user/add")
	public String addUser(@RequestBody UserInfo request) throws InvalidPersonalInformation {
		var user = new User();
		setNewUser(request, user);
		userServices.saveUser(user);
		return "Ok";
	}

	@PutMapping("/api/user/edit")
	public String editUser(@RequestBody UserInfo request) throws UserNotFoundException, InvalidPersonalInformation {
		var user = userServices.findUserById(request.getId());
		setNewUser(request, user);
		userServices.saveUser(user);
		return "Ok";
	}

	@GetMapping("/api/user/find")
	public UserInfo findUser(@RequestParam("userId") Long userId) throws UserNotFoundException {
		var user = userServices.findUserById(userId);
		return new UserInfo(user);
	}

	@DeleteMapping("/api/user/Delete/{id}")
	public String deleteUser(@PathVariable("id") Long userId){
		return "Possible at premium version id:" + userId ;
	}

	private void setNewUser(UserInfo oldUser, User newUser) throws InvalidPersonalInformation {
		newUser.setEmail(oldUser.getEmail());
		newUser.setFirstName(oldUser.getFirstName());
		newUser.setLastName(oldUser.getLastName());
		newUser.setPhoneNumber(oldUser.getPhoneNumber());
		var personalInformation = new Info();
		personalInformation.setAddress(oldUser.getAddress());
		personalInformation
				.setBirthDay(
						LocalDate.parse(oldUser.getBirthDay(), DateTimeFormatter.ofPattern(UserInfo.BIRTHDAY_FORMAT))
				);
		newUser.setPersonalInformation(personalInformation);
	}
}
