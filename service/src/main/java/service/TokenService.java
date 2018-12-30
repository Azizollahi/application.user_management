package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.TokenInformation;
import domain.TokenStore;
import infrastructure.repository.TokenStoreRepository;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Service;
import service.exceptions.TokenGenerationException;
import service.interfaces.ITokenService;

import java.io.IOException;

@Service
public class TokenService implements ITokenService {
	private TokenStoreRepository repository;

	public TokenService(TokenStoreRepository repository){
		this.repository = repository;
	}

	@Override
	public TokenInformation extractTokenInformation(String tokenStr, String secrete) throws IOException {
		var jwtToken = JwtHelper.decodeAndVerify(tokenStr,new MacSigner(secrete));
		return new ObjectMapper().readValue(jwtToken.getClaims(), TokenInformation.class);
	}

	@Override
	public String jamTokenInformation(TokenInformation tokenInformation, String secrete) throws TokenGenerationException {
		String plainToken;
		try {
			plainToken = new ObjectMapper().writeValueAsString(tokenInformation);
		} catch (JsonProcessingException e) {
			throw new TokenGenerationException("Couldn't grant a token");
		}
		var signer = new MacSigner(secrete);
		return JwtHelper.encode(plainToken, signer).getEncoded();
	}

	public void saveTokenStore(TokenStore tokenStore){
		repository.save(tokenStore);
	}
}
