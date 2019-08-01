import java.util.Stack;
import java.util.function.Function;
//
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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
	private char[] _certificate;

	public PlianceClientFactory(String secret, String issuer, String url, char[] certificate) {
		_secret = secret;
		_issuer = issuer;
		_baseUrl = url;
		_certificate = certificate;
	}

	public IPlianceClient Create(String givenName, String subject) {
		return new PlianceClient(this, givenName, subject);
	}

	public <T> T Execute(Function<HttpsURLConnection, T> action, String path, String givenName, String subject) throws Exception {
		HttpsURLConnection client = CreateHttpClient(path);

		client.setRequestProperty("Authorization", "Bearer " + CreateJwtToken(givenName, subject));

		try {
			return action.apply(client);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private HttpsURLConnection CreateHttpClient(String url) throws Exception {
		KeyStore clientStore = KeyStore.getInstance("PKCS12");
		clientStore.load(new FileInputStream(new File("client.pfx")), "".toCharArray());
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(clientStore, "".toCharArray());
		KeyManager[] kms = kmf.getKeyManagers();
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(kms, null, new SecureRandom());
		URL xurl = new URL(_baseUrl + url);
		HttpsURLConnection client = (HttpsURLConnection) xurl.openConnection();
		client.setRequestProperty("Accept", "application/json");
		client.setSSLSocketFactory(sslContext.getSocketFactory());

		return client;
	}

	private String CreateJwtToken(String givenName, String subject) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		byte[] apiKeySecretBytes = _secret.getBytes();
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		long expMillis = nowMillis + 1000 * 300;
		Date exp = new Date(expMillis);
		JwtBuilder builder = Jwts.builder().setId("id").setAudience("pliance.io").setIssuedAt(now).setExpiration(exp)
				.setSubject(subject).setIssuer(_issuer).signWith(signatureAlgorithm, signingKey);

		return builder.compact();
	}
}
