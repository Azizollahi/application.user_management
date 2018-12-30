package domain;

import com.google.common.base.Strings;
import domain.exception.InvalidTokenException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class TokenStore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	@Column(nullable = false, unique = true, length = 1024)
	private String token;
	@Column(nullable = false)
	private LocalTime expirationDateTime;
	@Column(nullable = false)
	private LocalDateTime startDateTime;
	@OneToOne(mappedBy = "token")
	private Account account;

	// no argument constructor required by jpa
	public TokenStore() {}

	public TokenStore(String token, LocalTime expirationDateTime){
		initialize(token, expirationDateTime, LocalDateTime.now());
	}

	public TokenStore(String token, LocalTime expirationDateTime, LocalDateTime startDateTime){
		initialize(token, expirationDateTime, startDateTime);
	}

	private void initialize(String token, LocalTime expirationDateTime, LocalDateTime startDateTime){
		this.token = token;
		this.expirationDateTime  = expirationDateTime;
		this.startDateTime  = startDateTime;
	}

	public Long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public void setStartDateTime(LocalDateTime startDateTime){
		this.startDateTime = startDateTime;
	}
	public LocalDateTime getStartDateTime(){
		return startDateTime;
	}

	public void setToken(String token) throws InvalidTokenException {
		if(Strings.isNullOrEmpty(token))
			throw new InvalidTokenException("Token can't be null or empty");
		this.token = token;
	}

	public LocalDateTime getExpirationDateTime() {
		return startDateTime.plusSeconds(expirationDateTime.getSecond());
	}
}
