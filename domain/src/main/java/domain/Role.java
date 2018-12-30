package domain;

import com.google.common.base.Strings;
import domain.exception.InvalidAccountException;
import domain.exception.InvalidPermissionException;
import domain.exception.InvalidRoleException;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	@Column(nullable = false, unique = true)
	private String title;
	@ManyToMany
	private List<Permission> permissions;
	@ManyToMany(mappedBy = "roles")
	private List<Account> accounts;

	// no argument constructor required by jpa
	public Role() {}

	public Role(String title){
		initialize(title);
	}

	private void initialize(String title){
		this.title = title;
		accounts = new LinkedList<>();
		permissions = new LinkedList<>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws InvalidRoleException {
		if(Strings.isNullOrEmpty(title))
			throw new InvalidRoleException("Title of role should not be null or empty");
		this.title = title;
	}
	public Long getId() {
		return id;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void addAccount(Account account) throws InvalidAccountException {
		if(account == null || account.getId() <= 0)
			throw new InvalidAccountException("Account can't be null or have an invalid");
		this.accounts.add(account);
	}

	public List<Permission> getPermissions() {
		return permissions;
	}
	public void addPermission(Permission permission) throws InvalidPermissionException {
		if(permission == null || permission.getId() <=0)
			throw new InvalidPermissionException("permission can't be null or have an invalid id");
		permissions.add(permission);
	}
}
