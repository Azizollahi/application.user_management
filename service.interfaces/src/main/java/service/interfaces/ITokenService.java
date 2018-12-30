package service.interfaces;

import domain.TokenInformation;
import domain.TokenStore;
import service.exceptions.TokenGenerationException;

import java.io.IOException;

public interface ITokenService {
	TokenInformation extractTokenInformation(String tokenStr, String secrete) throws IOException;
	String jamTokenInformation(TokenInformation tokenInformation, String secrete) throws TokenGenerationException;
	void saveTokenStore(TokenStore tokenStore);
}
