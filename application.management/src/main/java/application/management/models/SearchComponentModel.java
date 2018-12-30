package application.management.models;

public class SearchComponentModel {
	private String addSaveCallbackUrl;
	private String searchCallBackUrl;
	private String exportCallbackUrl;
	private String accountInfoCallbackUrl;
	public String getAddSaveCallbackUrl() {
		return addSaveCallbackUrl;
	}

	public void setAddSaveCallbackUrl(String addSaveCallbackUrl) {
		this.addSaveCallbackUrl = addSaveCallbackUrl;
	}

	public String getSearchCallBackUrl() {
		return searchCallBackUrl;
	}

	public void setSearchCallBackUrl(String searchCallBackUrl) {
		this.searchCallBackUrl = searchCallBackUrl;
	}

	public String getExportCallbackUrl() {
		return exportCallbackUrl;
	}

	public void setExportCallbackUrl(String exportCallbackUrl) {
		this.exportCallbackUrl = exportCallbackUrl;
	}

	public String getAccountInfoCallbackUrl() {
		return accountInfoCallbackUrl;
	}

	public void setAccountInfoCallbackUrl(String accountInfoCallbackUrl) {
		this.accountInfoCallbackUrl = accountInfoCallbackUrl;
	}
}
