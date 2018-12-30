package domain.management;

import javax.persistence.*;
@Entity(name = "view_management_table_header")
public class ViewManagementTableHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	public Long id;
	@Column
	private String managementName;
	private String headerName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getManagementName() {
		return managementName;
	}

	public void setManagementName(String managementName) {
		this.managementName = managementName;
	}
}
