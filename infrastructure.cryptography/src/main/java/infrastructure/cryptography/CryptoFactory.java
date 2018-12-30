package infrastructure.cryptography;

import infrastructure.cryptography.interfaces.IAesCryptoFactory;
import infrastructure.cryptography.interfaces.ICryptoFactory;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class CryptoFactory implements ICryptoFactory {
	@Override
	public IAesCryptoFactory buildByAesCryptoFactory() throws NoSuchPaddingException, NoSuchAlgorithmException {
		return new AesCryptoFactory();
	}
}
