package domain;

import com.google.common.base.Strings;
import domain.exception.InvalidAccountException;
import domain.exception.InvalidPersonalInformation;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "AUSER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, unique = true)
	private String phoneNumber;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@OneToOne
	@JoinColumn(name = "INFO_ID", referencedColumnName = "ID", nullable = false)
	private Info personalInformation;
	@OneToMany(mappedBy = "user")
	private List<Account> accounts;

	// no argument constructor required by jpa
	public User() {}

	public User(Info personalInformation, String firstName, String lastName, String email, String phoneNumber){
		inirialize(personalInformation, firstName, lastName, email, phoneNumber);
	}
	private void inirialize(Info personalInformation, String firstName, String lastName, String email, String phoneNumber){
		this.firstName = firstName;
		this.lastName  = lastName;
		this.email  = email;
		this.phoneNumber  = phoneNumber;
		this.personalInformation = personalInformation;
		accounts = new LinkedList<>();
	}

	public void addAccount(Account account) throws InvalidAccountException {
		if(account == null)
			throw new InvalidAccountException("The account should not be null");
		accounts.add(account);
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public Info getPersonalInformation() {
		return personalInformation;
	}

	public void setPersonalInformation(Info personalInformation) throws InvalidPersonalInformation {
		if(personalInformation == null)
			throw new InvalidPersonalInformation("The personal information can't be set as null");
		this.personalInformation = personalInformation;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws InvalidPersonalInformation {
		evaluateString("Email", email);
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidPersonalInformation {
		evaluateString("Phone number", phoneNumber);
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws InvalidPersonalInformation {
		evaluateString("First name", firstName);
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws InvalidPersonalInformation {
		evaluateString("Last name", lastName);
		this.lastName = lastName;
	}
	private void evaluateString(String targetName, String target) throws InvalidPersonalInformation {
		if(Strings.isNullOrEmpty(target))
			throw new InvalidPersonalInformation("The " + targetName + " should not be null or empty");
	}

	public Long getId() {
		return id;
	}
}
