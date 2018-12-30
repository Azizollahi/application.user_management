package application.auth.interceptors;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import service.auth.interfaces.IAuthFacadeService;
import service.interfaces.Resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Service
public class TokenAuthorization implements HandlerInterceptor {

	private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
	private IAuthFacadeService authFacadeService;

	@Autowired
	public TokenAuthorization(IAuthFacadeService authFacadeService){
		this.authFacadeService = authFacadeService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		var authorizerAnnotate = extractResourceAnnotation(handler);
		if(authorizerAnnotate == null)
			return true;
		var token = extractToken(request);
		if(Strings.isNullOrEmpty(token)) {
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return false;
		}
		var accountName = extractCookieName(request, "AccountName");
		var newToken = authFacadeService.authorize(token, authorizerAnnotate.name(), accountName);
		setResponseToken(response, newToken);
		return true;
	}
	private String extractCookieName(HttpServletRequest request, String cookieName){
		var cookie = Arrays.stream(request.getCookies()).filter(x-> x.getName().equals(cookieName)).findFirst();
		if(cookie.isPresent())
			return cookie.get().getValue();
		return "";
	}

	private void setResponseToken(HttpServletResponse response, String token) {
		response.setHeader(AUTHORIZATION_HEADER_NAME, token);
		response.addCookie(new Cookie(AUTHORIZATION_HEADER_NAME, token));
	}
	private String extractToken(HttpServletRequest request){
		var token = request.getHeader(AUTHORIZATION_HEADER_NAME);
		if(token == null)
			token = extractCookieName(request, AUTHORIZATION_HEADER_NAME);
		return token;
	}

	private Resource extractResourceAnnotation(Object handler){
		if(!handler.getClass().getTypeName().equals(HandlerMethod.class.getTypeName()))
			return null;
		var method = (HandlerMethod)handler;
		var authorizerAnnotate = method.getMethodAnnotation(Resource.class);
		if(authorizerAnnotate != null)
			return authorizerAnnotate;
		return null;
	}

}
