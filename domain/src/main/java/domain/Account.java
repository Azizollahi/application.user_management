package domain;

import com.google.common.base.Strings;
import domain.exception.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Account{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private String password;
	private int invalidLoginCount;
	private boolean lock;
	private boolean open;
	@Column(nullable = false)
	private LocalDateTime creationDateTime;
	private LocalDateTime lastInvalidLogin;
	private LocalDateTime lastLogin;
	private String picPath;
	@Column(nullable = false, unique = true)
	private String secret;
	@ManyToMany
	private List<Role> roles;
	@ManyToOne
	@JoinColumn(name = "User_ID", referencedColumnName = "ID", nullable = false)
	private User user;
	@OneToOne
	@JoinColumn(name = "Token_Id", referencedColumnName = "ID")
	private TokenStore token;

	// no argument constructor required by jpa
	public Account() {}
	public Account(User user, String name, String password){
		initialize(user, name, password);
	}

	private void initialize(User user, String name, String password){
		this.name = name;
		this.password  = password;
		this.user = user;
		this.roles = new LinkedList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidAccountNameException {
		if(Strings.isNullOrEmpty(name))
			throw new InvalidAccountNameException("The account name should not be null or empty");
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws InvalidPasswordException {
		if(Strings.isNullOrEmpty(password))
			throw new InvalidPasswordException("The password should not be null or empty");
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) throws InvalidUserException {
		if(user == null || user.getId()<=0)
			throw new InvalidUserException("User can't be null or have an invalid id");
		this.user = user;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) throws InvalidRoleException {
		if(role == null)
			throw new InvalidRoleException("Role can't be null");
		this.roles.add(role);
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) throws InvalidSecretKeyException {
		if(Strings.isNullOrEmpty(secret))
			throw new InvalidSecretKeyException("Account secret shouldn't be null or empty");
		this.secret = secret;
	}

	public TokenStore getToken() {
		return token;
	}

	public void setToken(TokenStore token) throws InvalidTokenException {
		if(token == null)
			throw new InvalidTokenException("token should not be null");
		this.token = token;
	}

	public int getInvalidLoginCount() {
		return invalidLoginCount;
	}

	public void setInvalidLoginCount(int invalidLoginCount) {
		this.invalidLoginCount = invalidLoginCount;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public LocalDateTime getLastInvalidLogin() {
		return lastInvalidLogin;
	}

	public void setLastInvalidLogin(LocalDateTime lastInvalidLogin) {
		this.lastInvalidLogin = lastInvalidLogin;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getPicPath() {
		if(Strings.isNullOrEmpty(picPath))
			return "img/defaultAccountPicture.png";
		return picPath;
	}

	public void setPicPath(String picPath) {
		if(Strings.isNullOrEmpty(picPath))
			this.picPath = "img/defaultAccountPicture.png";
		else
			this.picPath = picPath;
	}

	public void update(Account account){
		name = account.getName();
		password = account.getPassword();
		secret = account.getSecret();
		lock = account.isLock();
		open = account.isOpen();

	}
}
