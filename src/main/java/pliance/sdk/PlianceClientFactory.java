package pliance.sdk;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import io.jsonwebtoken.*;
import pliance.sdk.exceptions.PlianceApiException;
import java.util.Date;

public class PlianceClientFactory {

	private String _secret;
	private String _issuer;
	private String _baseUrl;
	private KeyStore _certificate;

	public PlianceClientFactory(String secret, String issuer, String url, KeyStore certificate) {
		_secret = secret;
		_issuer = issuer;
		_baseUrl = url;
		_certificate = certificate;
	}

	public IPlianceClient create(String givenName, String subject) {
		return new PlianceClient(this, givenName, subject);
	}

	public <T> T execute(String method, Func<HttpURLConnection, T, Exception> action, String path, String givenName,
			String subject) throws PlianceApiException {

		try {
			HttpURLConnection client = createHttpClient(path);

			client.setRequestProperty("Authorization", "Bearer " + createJwtToken(givenName, subject));
            client.setRequestProperty("User-Agent", "Pliance.Java.SDK:VERSION");
			client.setDoInput(true);
			client.setDoOutput(true);
			client.setRequestMethod(method);

			return action.accept(client);
		} catch (Exception ex) {
			throw new PlianceApiException(ex.getMessage(), ex);
		}
	}

	private HttpURLConnection createHttpClient(String path) throws Exception {
		URL url = new URL(_baseUrl + path);
		String protocol = url.getProtocol();
		
		if (protocol.equals("https")) {
			HttpsURLConnection client = (HttpsURLConnection) url.openConnection();
			client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			client.setRequestProperty("Accept", "application/json");

			if (_certificate != null) {
				KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				kmf.init(_certificate, "".toCharArray());
				KeyManager[] kms = kmf.getKeyManagers();
				SSLContext sslContext = SSLContext.getInstance("TLS");
				sslContext.init(kms, null, new SecureRandom());
				client.setSSLSocketFactory(sslContext.getSocketFactory());
			}

			return client;
		} else if (protocol.equals("http")) {
			HttpURLConnection client = (HttpURLConnection) url.openConnection();
			client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			client.setRequestProperty("Accept", "application/json");

			return client;
		} else {
			throw new Exception("Unknown protocol: '" + protocol + "'");
		}
	}

	private String createJwtToken(String givenName, String subject) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		byte[] apiKeySecretBytes = _secret.getBytes();
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		long expMillis = nowMillis + 1000 * 300;
		Date exp = new Date(expMillis);
		JwtBuilder builder = Jwts.builder().setAudience("pliance.io").setNotBefore(now).setIssuedAt(now)
				.setExpiration(exp).setSubject(subject).setIssuer(_issuer).claim("given_name", givenName)
				.signWith(signingKey, signatureAlgorithm);

		return builder.compact();
	}
}
