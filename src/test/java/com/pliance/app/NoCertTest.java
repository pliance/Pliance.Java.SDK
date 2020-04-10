package com.pliance.app;

import junit.framework.Test;
import junit.framework.TestSuite;
import pliance.sdk.PlianceClientFactory;
import pliance.sdk.contracts.*;

public class NoCertTest extends TestBase {

	public NoCertTest() throws Exception {
	}

	public static Test suite() {
		return new TestSuite(NoCertTest.class);
	}

	protected PlianceClientFactory createFactory() {
		return new PlianceClientFactory(_secret, _issuer, "https://local-no-cert.pliance.io/", null);
	}
	
	public void test_NoCert() throws Exception
	{
		PingQuery query = new PingQuery();
		
		_client.ping(query);
	}		
}
