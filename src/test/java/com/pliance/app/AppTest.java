package com.pliance.app;

import java.io.File;
import java.io.FileInputStream;

import com.google.gson.Gson;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import pliance.sdk.IPlianceClient;
import pliance.sdk.PlianceClientFactory;
import pliance.sdk.Contracts.*;

public class AppTest extends TestCase {
	private PlianceClientFactory _factory;
	private IPlianceClient _client;

	public AppTest(String testName) throws Exception {
		super(testName);

		FileInputStream certificate = new FileInputStream(
				new File("/home/dyluck/Plisec/Examples/Java/Example/client.pfx"));
		_factory = new PlianceClientFactory("2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b", "Test",
				"https://test-stage3.pliance.io/", certificate);
		_client = _factory.Create("givenname", "subject");
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testRegisterPersonCommand() throws Exception {
		RegisterPersonCommand command = new RegisterPersonCommand();

		command.personReferenceId = "reference-id";
		command.firstName = "Adam";
		command.lastName = "Anvandare";

		RegisterPersonResponse response = _client.RegisterPerson(command);
		Gson gson = new Gson();
		String json = gson.toJson(response);

		System.out.println("response: " + json);
	}
	
	public void testViewPerson() throws Exception {
		ViewPersonQuery query = new ViewPersonQuery();

		query.personReferenceId = "customer/2";

		ViewPersonQueryResult response = _client.ViewPerson(query);
		Gson gson = new Gson();
		String json = gson.toJson(response);

		System.out.println("response: " + json);
	}	
	
	public void testPing() throws Exception {
		PingResponse response = _client.Ping();
		Gson gson = new Gson();
		String json = gson.toJson(response);

		System.out.println("response: " + json);
	}		
}
