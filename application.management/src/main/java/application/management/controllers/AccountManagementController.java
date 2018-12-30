package application.management.controllers;

import application.management.models.SearchComponentModel;
import application.management.models.TableComponentModel;
import application.management.utils.CookieService;
import domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.interfaces.IAccountService;
import service.management.interfaces.ITableComponentService;
import service.management.interfaces.IDashboardPageService;
import service.management.interfaces.IUserInformationService;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AccountManagementController {

	private IUserInformationService userInformationService;
	private IDashboardPageService dashboardPageService;
	private ITableComponentService accountTableComponentService;
	private IAccountService accountService;

	@Autowired
	public AccountManagementController(IUserInformationService userInformationService,
									   IDashboardPageService dashboardPageService
			, ITableComponentService accountTableComponentService, IAccountService accountService){
		this.userInformationService = userInformationService;
		this.dashboardPageService = dashboardPageService;
		this.accountTableComponentService = accountTableComponentService;
		this.accountService = accountService;
	}

	@GetMapping(value = "/dashboard/accountManagement")
	public ModelAndView accountManagement(HttpServletRequest request) throws AccountNotFoundException {
		var accountName = findAccountNameCookie(request).getValue();
		var account = accountService.findAccountByName(accountName);
		var tableComponents = new TableComponentModel();
		satisfyTableComponentCallbackUrls(tableComponents);
		tableComponents.setHeaderNames(accountTableComponentService.findAllHeaders("AccountManagement"));
		var searchComponentModel = satisfySearchComponent();
		return packPageBeans(tableComponents, searchComponentModel, account);
	}

	@PostMapping(value= "/dashboard/accountManagement")
	public ModelAndView searchAccounts(
			HttpServletRequest request, @RequestParam String searchName)
			throws AccountNotFoundException {
		var accountName = findAccountNameCookie(request).getValue();
		var account = accountService.findAccountByName(accountName);
		var accounts = accountService.findAccountsLike(searchName);
		var tableComponents = new TableComponentModel();
		satisfyTableComponentCallbackUrls(tableComponents);
		satisfyAccountInfo(accounts, tableComponents);
		var searchComponentModel = satisfySearchComponent();
		tableComponents.setHeaderNames(accountTableComponentService.findAllHeaders("AccountManagement"));
		return packPageBeans(tableComponents, searchComponentModel, account);
	}

	private ModelAndView packPageBeans(TableComponentModel tabVals,SearchComponentModel searchComponentModel, Account account ){
		var modelAndView = new ModelAndView("dashboard/Management");
		modelAndView.addObject("tableComponent", tabVals);
		modelAndView.addObject("searchComponent", searchComponentModel);
		addUserInfo(modelAndView, account);
		addDashboardHeaderInfo(modelAndView);
		return modelAndView;
	}
	private SearchComponentModel satisfySearchComponent(){
		var searchComponentModel = new SearchComponentModel();
		var callBackUrl = "accountManagement";
		searchComponentModel.setAddSaveCallbackUrl(callBackUrl);
		searchComponentModel.setExportCallbackUrl(callBackUrl);
		searchComponentModel.setSearchCallBackUrl("/dashboard/accountManagement");
		searchComponentModel.setAccountInfoCallbackUrl(callBackUrl);
		return searchComponentModel;
	}
	private void addUserInfo(ModelAndView modelAndView, Account account) {
		var userInfo = userInformationService.satisfyUserInfo(account);
		modelAndView.addObject("userInfo", userInfo);
	}
	private void addDashboardHeaderInfo(ModelAndView modelAndView){
		var headerInfo = dashboardPageService.findHeaderInfo("Dashboard");
		modelAndView.addObject("headerInfo",headerInfo);
	}
	private Cookie findAccountNameCookie(HttpServletRequest request) throws AccountNotFoundException {
		return CookieService.findCookieByName(request, "AccountName");
	}
	private void satisfyAccountInfo(List<Account> accountList, TableComponentModel destination){
		List<List<String>> values = new LinkedList<>();
		for (var account :
				accountList) {
			var row = new LinkedList<String>();
			row.add(String.valueOf(account.getId()));
			row.add(account.getUser().getFirstName() + " " + account.getUser().getLastName());
			row.add(account.getName());
			if(account.getLastLogin() != null)
				row.add(account.getLastLogin().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")));
			else
				row.add("");
			if(account.getLastInvalidLogin() != null)
				row.add(account.getLastInvalidLogin().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")));
			else
				row.add("");
			row.add(String.valueOf(account.isLock()));
			row.add(String.valueOf(account.isOpen()));
			if(account.getCreationDateTime() != null)
				row.add(account.getCreationDateTime().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
			else
				row.add("");
			values.add(row);
		}
		destination.setTableValues(values);
	}
	private void satisfyTableComponentCallbackUrls(TableComponentModel tableComponentModel){
		tableComponentModel.setDeleteCallbackUrl("/api/account/delete");
		tableComponentModel.setExportCallbackUrl("accountManagement");
		tableComponentModel.setEditSaveCallbackUrl("/api/account/edit");
		tableComponentModel.setAccountCallbackUrl("/api/account/find");
	}
}
