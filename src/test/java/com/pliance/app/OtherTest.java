package com.pliance.app;

import junit.framework.Test;
import junit.framework.TestSuite;
import pliance.sdk.contracts.PingQuery;
import pliance.sdk.IPlianceClient;
import pliance.sdk.PlianceClientFactory;
import pliance.sdk.contracts.*;
import pliance.sdk.exceptions.PlianceApiException;

public class OtherTest extends TestBase {
	public static Test suite() {
		return new TestSuite(OtherTest.class);
	}

	public OtherTest() throws Exception {
	}

	public void test_Ping() throws Exception {
		PingQuery query = new PingQuery();
		
		_client.ping(query);
	}

	public void test_PingNoCert() throws Exception {
		PingQuery query = new PingQuery();
		
		_certificate = null;
		_url = "https://local-no-cert.pliance.io/";
		PlianceClientFactory factory = createFactory();
		IPlianceClient client = factory.create("Adam", "42");

		client.ping(query);
	}
	
	public void test_BadRequest() throws Exception
	{
		assertThrows(PlianceApiException.class, () -> {
			ViewPersonQuery query = new ViewPersonQuery();
			query.personReferenceId = _referenceId;

			_client.viewPerson(query);
		});
	}
}
