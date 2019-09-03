package pliance.sdk;

import java.io.FileInputStream;
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

import java.util.Date;

public class PlianceClientFactory {

	private String _secret;
	private String _issuer;
	private String _baseUrl;
	private FileInputStream _certificate;

	public PlianceClientFactory(String secret, String issuer, String url, FileInputStream certificate) {
		_secret = secret;
		_issuer = issuer;
		_baseUrl = url;
		_certificate = certificate;
	}

	public IPlianceClient Create(String givenName, String subject) {
		return new PlianceClient(this, givenName, subject);
	}

	public <T> T Execute(String method, Func1<HttpURLConnection, T, Exception> action, String path, String givenName,
			String subject) throws Exception {

		HttpURLConnection client = CreateHttpClient(path, method);

		client.setRequestProperty("Authorization", "Bearer " + CreateJwtToken(givenName, subject));

		return action.accept(client);
	}

	private HttpURLConnection CreateHttpClient(String url, String method) throws Exception {
		URL xurl = new URL(_baseUrl + url);

		if (xurl.getProtocol() == "https") {
			KeyStore clientStore = KeyStore.getInstance("PKCS12");
			clientStore.load(_certificate, "".toCharArray());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(clientStore, "".toCharArray());
			KeyManager[] kms = kmf.getKeyManagers();
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kms, null, new SecureRandom());
			System.out.println("Url: " + xurl);
			HttpsURLConnection client = (HttpsURLConnection) xurl.openConnection();
			client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			client.setRequestProperty("Accept", "application/json");
			client.setSSLSocketFactory(sslContext.getSocketFactory());
			client.setDoInput(true);
			client.setDoOutput(true);
			client.setRequestMethod(method);

			return client;
		} else {
			HttpURLConnection client = (HttpURLConnection) xurl.openConnection();
			client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			client.setRequestProperty("Accept", "application/json");
			client.setDoInput(true);
			client.setDoOutput(true);
			client.setRequestMethod(method);

			return client;
		}
	}

	private String CreateJwtToken(String givenName, String subject) {
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
