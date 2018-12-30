package application.management.utils;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieService {

	private CookieService(){

	}

	public static Cookie findCookieByName(HttpServletRequest request, String name) throws AccountNotFoundException {
		var foundCookie = Arrays.stream(request.getCookies()).filter(x -> x.getName().equals(name)).findFirst();
		if(foundCookie.isPresent())
			return foundCookie.get();
		throw new AccountNotFoundException("Account not found!");
	}
}
