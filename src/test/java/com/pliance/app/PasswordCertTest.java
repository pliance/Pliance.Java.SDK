package com.pliance.app;

import java.io.FileInputStream;
import java.security.KeyStore;
import junit.framework.Test;
import junit.framework.TestSuite;
import pliance.sdk.contracts.*;

public class PasswordCertTest extends TestBase {

	public PasswordCertTest() throws Exception {
		_certificate = KeyStore.getInstance("PKCS12");
		_certificate.load(new FileInputStream("client-password.pfx"), "password".toCharArray());			
	}

	public static Test suite() {
		return new TestSuite(PasswordCertTest.class);
	}

	public void test_NoCert() throws Exception
	{
		PingQuery query = new PingQuery();
		
		_client.ping(query);
	}		
}
