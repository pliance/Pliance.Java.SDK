package com.pliance.app;

import java.io.FileInputStream;
import java.util.UUID;
import junit.framework.TestCase;
import pliance.sdk.IPlianceClient;
import pliance.sdk.PlianceClientFactory;
import pliance.sdk.Action;

public abstract class TestBase extends TestCase {
	protected PlianceClientFactory _factory;
	protected IPlianceClient _client;
	protected String _secret;
	protected String _issuer;
	protected String _url;
	protected FileInputStream _certificate;
	protected String _referenceId;

	protected TestBase() {
		_secret = "2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b";
		_issuer = "Demo";
		_url = "http://localhost:5777/";
		_certificate = null;
		_factory = createFactory();
		_client = _factory.create("Adam", "42");
		_referenceId = UUID.randomUUID().toString();
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
