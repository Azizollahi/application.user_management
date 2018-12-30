package application.management.models;

import java.util.LinkedList;
import java.util.List;

public class TableComponentModel {
	private List<List<String>> tableValues;
	private String accountCallbackUrl;
	private String exportCallbackUrl;
	private String deleteCallbackUrl;
	private String editSaveCallbackUrl;
	private List<String> headerNames;

	public TableComponentModel(){
		tableValues = new LinkedList<>();
	}

	public List<List<String>> getTableValues() {
		return tableValues;
	}

	public void setTableValues(List<List<String>> values) {
		this.tableValues = values;
	}

	public String getExportCallbackUrl() {
		return exportCallbackUrl;
	}

	public void setExportCallbackUrl(String exportCallbackUrl) {
		this.exportCallbackUrl = exportCallbackUrl;
	}

	public String getDeleteCallbackUrl() {
		return deleteCallbackUrl;
	}

	public void setDeleteCallbackUrl(String deleteCallbackUrl) {
		this.deleteCallbackUrl = deleteCallbackUrl;
	}

	public String getEditSaveCallbackUrl() {
		return editSaveCallbackUrl;
	}

	public void setEditSaveCallbackUrl(String editSaveCallbackUrl) {
		this.editSaveCallbackUrl = editSaveCallbackUrl;
	}

	public List<String> getHeaderNames() {
		return headerNames;
	}

	public void setHeaderNames(List<String> headerNames) {
		this.headerNames = headerNames;
	}

	public String getAccountCallbackUrl() {
		return accountCallbackUrl;
	}

	public void setAccountCallbackUrl(String accountCallbackUrl) {
		this.accountCallbackUrl = accountCallbackUrl;
	}
}
