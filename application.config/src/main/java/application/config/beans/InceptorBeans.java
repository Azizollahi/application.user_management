package application.config.beans;

import application.auth.interceptors.TokenAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import service.auth.interfaces.IAuthFacadeService;

@Configuration
public class InceptorBeans {
	private IAuthFacadeService authFacadeService;

	@Autowired
	public InceptorBeans(IAuthFacadeService authFacadeService){
		this.authFacadeService = authFacadeService;
	}

	@Bean
	public HandlerInterceptor createTokenAuthoriztionInceptor(){
		return new TokenAuthorization(authFacadeService);
	}
}
