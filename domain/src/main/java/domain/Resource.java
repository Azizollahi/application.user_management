package domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	@Column(nullable = false, unique = true)
	private String name;
	@ManyToMany
	private List<Permission> permissions;

	public List<Permission> getPermissions() {
		return permissions;
	}
}
