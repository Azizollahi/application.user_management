package infrastructure.cryptography;

import infrastructure.cryptography.interfaces.IAesCryptoFactory;
import infrastructure.cryptography.interfaces.ICryptoActor;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class AesCryptoFactory implements IAesCryptoFactory {

	private String mode;
	private String padding;

	@Override
	public IAesCryptoFactory byUsingCbcMode(){
		mode = "CBC";
		return this;
	}

	@Override
	public IAesCryptoFactory byUsingEcbMode(){
		mode = "ECB";
		return this;
	}

	@Override
	public IAesCryptoFactory byUsingPKCS5Padding(){
		padding = "PKCS5PADDING";
		return this;
	}

	@Override
	public ICryptoActor build() throws NoSuchAlgorithmException, NoSuchPaddingException {
		return new AesCryptoActor(mode, padding);
	}
}
