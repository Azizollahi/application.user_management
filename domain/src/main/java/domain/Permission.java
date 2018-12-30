package domain;

import com.google.common.base.Strings;
import domain.exception.InvalidPermissionException;

import javax.persistence.*;
import java.util.List;

@Entity
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	@Column(nullable = false, unique = true)
	private String name;
	@ManyToMany(mappedBy = "permissions")
	private List<Role> roles;
	@ManyToMany(mappedBy = "permissions")
	private List<Resource> resources;

	// no argument constructor required by jpa
	public Permission() {}

	public Permission(String name) {
		initialize(name);
	}

	private void initialize(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidPermissionException {
		if(Strings.isNullOrEmpty(name))
			throw new InvalidPermissionException("permission name should not be null or empty");
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public List<Role> getRole() {
		return roles;
	}

	public List<Resource> getResources() {
		return resources;
	}
}
