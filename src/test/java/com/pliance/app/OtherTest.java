package com.pliance.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

	public void test_BadRequest() throws Exception {
		assertThrows(PlianceApiException.class, () -> {
			ViewPersonQuery query = new ViewPersonQuery();
			query.personReferenceId = _id;

			_client.viewPerson(query);
		});
	}

	public void test_Feed() throws Exception {
		FeedQuery command = new FeedQuery();

		FeedQueryResult response = _client.feed(command);

		assertTrue(response.success);
	}

	public void test_SaveWebhook() throws Exception {
		WebhookUpdateCommand command = new WebhookUpdateCommand();
		command.enabled = true;
		command.url = "https://url";
		command.secret = "secret";

		WebhookUpdateResponse response = _client.saveWebhook(command);

		assertTrue(response.success);
	}

	public void test_GetWebhook() throws Exception {
		WebhookQuery command = new WebhookQuery();

		WebhookQueryResult response = _client.getWebhook(command);

		assertTrue(response.success);
	}

	public void test_Gson() throws Exception {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").setPrettyPrinting().create();
		String json = "{ \"data\": {\"lastChanged\":{\"timestampUtc\":\"2020-10-13T15:15:56.199767Z\",\"checkpoint\":\"142e45a6a34b35759dded23b5ccc06349031a97baf0ed56e8c43c01d07b5c093\"}},\"status\":\"Success\",\"success\":true,\"message\":null,\"checkpoint\":\"142e45a6a34b35759dded23b5ccc06349031a97baf0ed56e8c43c01d07b5c093\"}";
		RegisterPersonResponse result = gson.fromJson(json, RegisterPersonResponse.class);
		
		assertEquals("Tue Oct 13 15:19:15 CEST 2020", result.data.lastChanged.timestampUtc.toString());
	}
}
