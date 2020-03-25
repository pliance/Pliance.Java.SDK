package com.pliance.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.UUID;
import junit.framework.TestCase;
import pliance.sdk.IPlianceClient;
import pliance.sdk.PlianceClientFactory;

public abstract class TestBase extends TestCase {
	protected PlianceClientFactory _factory;
	protected IPlianceClient _client;
	protected String _secret;
	protected String _issuer;
	protected String _url;
	protected KeyStore _certificate;
	protected String _referenceId;

	protected TestBase() throws Exception {
		FileInputStream file = null;

		try {
			file = new FileInputStream(new File("client.pfx"));
		} catch (Exception e) {
		}

		_secret = "2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b";
		_issuer = "Demo";
		_url = "https://local.pliance.io/";
		_factory = createFactory();
		_client = _factory.create("Adam", "42");
		_referenceId = UUID.randomUUID().toString();
		_certificate = KeyStore.getInstance("PKCS12");
		_certificate.load(new FileInputStream("client.pfx"), "".toCharArray());
	}

	private PlianceClientFactory createFactory() {
		return new PlianceClientFactory(_secret, _issuer, _url, _certificate);
	}

	protected <T extends Throwable> void assertThrows(Class<T> exceptionType, Action<Exception> func) throws Exception {
		try {
			func.accept();
		} catch (Exception ex) {
			if (exceptionType.isInstance(ex)) {
				return;
			} else {
				throw ex;
			}
		}
	}
}
