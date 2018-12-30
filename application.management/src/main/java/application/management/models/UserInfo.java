package application.management.models;

import domain.User;
import java.time.format.DateTimeFormatter;

public class UserInfo {
	public static final String BIRTHDAY_FORMAT = "MM/dd/yyyy";

	public UserInfo(){}

	public UserInfo(User user){
		id = user.getId();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		email = user.getEmail();
		phoneNumber = user.getPhoneNumber();
		address = user.getPersonalInformation().getAddress();
		birthDay = user.getPersonalInformation().getBirthDay().format(DateTimeFormatter.ofPattern(BIRTHDAY_FORMAT));
	}

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String birthDay;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
}
