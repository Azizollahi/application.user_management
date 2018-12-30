package infrastructure.cryptography.interfaces;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public interface IFactory<T> {
	T build() throws NoSuchAlgorithmException, NoSuchPaddingException;
}
