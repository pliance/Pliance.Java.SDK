package com.pliance.app;

import com.google.gson.Gson;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import pliance.sdk.IPlianceClient;
import pliance.sdk.PlianceClientFactory;
import pliance.sdk.contracts.*;

public class AppStage3Test extends TestCase {
	private PlianceClientFactory _factory;
	private IPlianceClient _client;
	private Gson _gson;

	public AppStage3Test(String testName) throws Exception {
		super(testName);

		_factory = new PlianceClientFactory("2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b", "Test",
				"https://test-stage3.pliance.io/", null);
		_client = _factory.Create("givenname", "subject");
		_gson = new Gson();
	}

	public static Test suite() {
		return new TestSuite(AppStage3Test.class);
	}

	public void testPing2() throws Exception {
		PingResponse response = _client.Ping();

		String json = _gson.toJson(response);

		assertEquals(_client.Source(), json);
	}
}
