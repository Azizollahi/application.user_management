package application.auth.controllers;

import domain.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.auth.interfaces.IAuthFacadeService;
import service.exceptions.NotAuthorizedException;
import service.exceptions.TokenGenerationException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

@Controller
public class AuthenticationController {
	private IAuthFacadeService authFacadeService;
	@Autowired
	public AuthenticationController(IAuthFacadeService authFacadeService){
		this.authFacadeService= authFacadeService;
	}

	@GetMapping(value = "login")
	public ModelAndView login(){
		return new ModelAndView("Login");
	}

	@PostMapping(value = "login")
	public ModelAndView login(HttpServletResponse response, LoginRequest requestModel) throws TokenGenerationException,
			NotAuthorizedException, InvalidTokenException, IllegalBlockSizeException, BadPaddingException,
			InvalidAlgorithmParameterException, InvalidKeyException, AccountNotFoundException {
		var token = authFacadeService.authenticate(requestModel.getUserName(), requestModel.getPassword());
		response.addCookie(new Cookie("Authorization", token));
		response.addCookie(new Cookie("AccountName", requestModel.getUserName()));
		return new ModelAndView("redirect:/dashboard");
	}
}
