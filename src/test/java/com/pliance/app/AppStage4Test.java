package com.pliance.app;

import java.io.File;
import java.io.FileInputStream;
import com.google.gson.Gson;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import pliance.sdk.IPlianceClient;
import pliance.sdk.PlianceClientFactory;
import pliance.sdk.contracts.*;

public class AppStage4Test extends TestCase {
	private PlianceClientFactory _factory;
	private IPlianceClient _client;
	private Gson _gson;

	public AppStage4Test(String testName) throws Exception {
		super(testName);

		FileInputStream certificate = new FileInputStream(
				new File("/home/dyluck/Plisec/Examples/Java/Example/client.pfx"));
		_factory = new PlianceClientFactory("2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b", "Test",
				"https://test-stage4.pliance.io/", certificate);
		_client = _factory.Create("givenname", "subject");
		_gson = new Gson();
	}

	public static Test suite() {
		return new TestSuite(AppStage4Test.class);
	}

	public void testRegisterPersonCommand() throws Exception {
		RegisterPersonCommand command = new RegisterPersonCommand();

		command.personReferenceId = "reference-id";
		command.firstName = "Adam";
		command.lastName = "Anvandare";

		RegisterPersonResponse response = _client.RegisterPerson(command);
		String json = _gson.toJson(response);

		assertEquals(_client.Source(), json);
	}

	public void testViewPerson() throws Exception {
		ViewPersonQuery query = new ViewPersonQuery();

		query.personReferenceId = "customer/2";

		ViewPersonQueryResult response = _client.ViewPerson(query);
		String json = _gson.toJson(response);

		assertEquals(_client.Source(), json);
	}
	
	public void testSearchPerson() throws Exception {
		PersonSearchQuery query = new PersonSearchQuery();

		query.query = "donald";

		PersonSearchQueryResult response = _client.SearchPerson(query);
		String json = _gson.toJson(response);

		assertEquals(_client.Source(), json);
	}

	public void testPing() throws Exception {
		PingResponse response = _client.Ping();
		String json = _gson.toJson(response);

		assertEquals(_client.Source(), json);
	}
}
