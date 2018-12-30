package infrastructure.cryptography.interfaces;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.Closeable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public interface ICryptoActor extends Closeable {
	static String transferToString(byte[] data){
		return new String(data);
	}
	byte[] encrypt(byte[] data) throws BadPaddingException, IllegalBlockSizeException,
			InvalidAlgorithmParameterException, InvalidKeyException;
	byte[] decrypt(byte[] data) throws BadPaddingException, IllegalBlockSizeException,
			InvalidAlgorithmParameterException, InvalidKeyException;
	ICryptoActor byUsingKey(byte[] key);
	ICryptoActor byUsingIvKey(byte[] iv);
}
