package domain;

import domain.exception.InvalidPersonalInformation;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Info{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	@Column(nullable = false)
	private LocalDate birthDay;
	private String address;
	@OneToOne(mappedBy = "personalInformation")
	private User user;

	// no argument constructor required by jpa
	public Info() {}

	public Info(LocalDate birthDay){
		initialize(birthDay);
	}

	private void initialize(LocalDate birthDay){
		this.birthDay  = birthDay;
	}



	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) throws InvalidPersonalInformation {
		if(birthDay == null)
			throw new InvalidPersonalInformation("Birthday should not be null");
		this.birthDay = birthDay;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
