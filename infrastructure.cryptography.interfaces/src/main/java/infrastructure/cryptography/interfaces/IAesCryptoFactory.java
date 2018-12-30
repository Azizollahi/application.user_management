package infrastructure.cryptography.interfaces;

public interface IAesCryptoFactory extends IFactory<ICryptoActor> {
	IAesCryptoFactory byUsingCbcMode();
	IAesCryptoFactory byUsingEcbMode();
	IAesCryptoFactory byUsingPKCS5Padding();
}
