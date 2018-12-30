package application.management.controllers;

import application.management.utils.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import service.interfaces.IAccountService;
import service.interfaces.Resource;
import service.management.interfaces.IDashboardPageService;
import service.management.interfaces.IUserInformationService;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {
	private IUserInformationService userInformationService;
	private IDashboardPageService dashboadPageService;
	private IAccountService accountService;
	@Autowired
	public DashboardController(IUserInformationService userInformationService,
							   IDashboardPageService dashboadPageService1, IAccountService accountService){
		this.userInformationService = userInformationService;
		this.dashboadPageService = dashboadPageService1;
		this.accountService = accountService;
	}

	@GetMapping(value = "/dashboard")
	@Resource(name = "dashboard_management")
	public ModelAndView dashboard(HttpServletRequest request) throws AccountNotFoundException {
		var foundCookie = findAccountNameCookie(request);
		return satisfyPageInfo(foundCookie.getValue());
	}

	private Cookie findAccountNameCookie(HttpServletRequest request) throws AccountNotFoundException {
		return CookieService.findCookieByName(request, "AccountName");
	}

	private ModelAndView satisfyPageInfo(String accountName){
		var modelAndView = new ModelAndView("dashboard/index");
		var account = accountService.findAccountByName(accountName);
		var info = userInformationService.satisfyUserInfo(account);
		var cardsInfo = dashboadPageService.findCardsInfo("Dashboard");
		var headerInfo = dashboadPageService.findHeaderInfo("Dashboard");
		modelAndView.addObject("userInfo", info);
		modelAndView.addObject("cardsInfo", cardsInfo);
		modelAndView.addObject("headerInfo", headerInfo);
		return modelAndView;
	}
}
