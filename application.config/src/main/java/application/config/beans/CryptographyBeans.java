package application.config.beans;

import infrastructure.cryptography.CryptoFactory;
import infrastructure.cryptography.interfaces.ICryptoActor;
import infrastructure.cryptography.interfaces.ICryptoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;


@Configuration
public class CryptographyBeans {

	private ICryptoFactory cryptoFactory;

	public CryptographyBeans(){
		cryptoFactory = new CryptoFactory();
	}

	@Bean
	@SessionScope
	public ICryptoActor buildCryptoActor() throws NoSuchAlgorithmException, NoSuchPaddingException {
		return cryptoFactory
				.buildByAesCryptoFactory()
				.byUsingCbcMode()
				.byUsingPKCS5Padding()
				.build();
	}
}
