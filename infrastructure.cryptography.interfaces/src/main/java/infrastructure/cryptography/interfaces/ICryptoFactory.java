package infrastructure.cryptography.interfaces;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public interface ICryptoFactory {
	IAesCryptoFactory buildByAesCryptoFactory() throws NoSuchPaddingException, NoSuchAlgorithmException;
}
