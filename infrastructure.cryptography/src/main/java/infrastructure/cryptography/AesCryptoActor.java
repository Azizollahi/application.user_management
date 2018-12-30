package infrastructure.cryptography;
import infrastructure.cryptography.interfaces.ICryptoActor;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AesCryptoActor implements ICryptoActor {
	private byte[] iv;
	private byte[] key;
	private IvParameterSpec ivParameterSpec;
	private SecretKeySpec secretKeySpec;
	private Cipher cipher;

	public AesCryptoActor(String mode, String padding) throws NoSuchPaddingException, NoSuchAlgorithmException {
		cipher = Cipher.getInstance("AES/"+mode+"/"+padding);
		iv = new byte[] {
				0x00, 0x00, 0x00,
				0x00, 0x00, 0x00,
				0x00, 0x00, 0x00,
				0x00, 0x00, 0x00,
				0x00, 0x00,0x00,
				0x00
		};
	}

	@Override
	public byte[] encrypt(byte[] data) throws BadPaddingException, IllegalBlockSizeException,
			InvalidAlgorithmParameterException, InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("key is not filled properly");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		return cipher.doFinal(data);
	}

	@Override
	public byte[] decrypt(byte[] data) throws BadPaddingException, IllegalBlockSizeException,
			InvalidAlgorithmParameterException, InvalidKeyException {
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
		return cipher.doFinal(data);
	}

	@Override
	public void close() throws IOException {
		// ignored, duo to no connection to any external device
	}

	@Override
	public ICryptoActor byUsingKey(byte[] key) {
		ivParameterSpec = new IvParameterSpec(iv);
		secretKeySpec = new SecretKeySpec(key, "AES");
		this.key = key;
		return this;
	}

	@Override
	public ICryptoActor byUsingIvKey(byte[] iv){
		System.arraycopy(iv, 0, this.iv, 0, iv.length);
		return this;
	}
}
