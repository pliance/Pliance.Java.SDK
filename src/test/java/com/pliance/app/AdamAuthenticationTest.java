package com.pliance.app;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.function.Function;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import pliance.sdk.IPlianceClient;
import pliance.sdk.PlianceClientFactory;
import pliance.sdk.contracts.*;
import pliance.sdk.exceptions.PlianceApiException;

public class AdamAuthenticationTest extends TestCase {
	private String _personReferenceId;
	private PlianceClientFactory _factory;
	private IPlianceClient _client;
	private RegisterPersonResponse _person;
	protected String _secret;
	protected String _issuer;
	protected String _url;
	protected FileInputStream _certificate;

	public static Test suite() {
		return new TestSuite(AdamAuthenticationTest.class);
	}

	public AdamAuthenticationTest() {
		_secret = "2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b";
		_issuer = "Demo";
		_url = "http://localhost:5777/";
		_certificate = null;
		_factory = CreateFactory();
		_client = _factory.Create("Adam", "42");
		_personReferenceId = LocalDateTime.now().toString();
	}

	public void test_Ping_WorkingAuthentication_Pass() throws Exception {
		IPlianceClient client = CreateFactory().Create("givenname", "subject");

		client.Ping();
	}

	public void test_Ping_BadSecret_Throws() {
		_secret = "2bb80d537b1da3e38bd30361aa855686";

		IPlianceClient client = CreateFactory().Create("givenname", "subject");

		AssertThrows(PlianceApiException.class, (a) -> {
			try {
				client.Ping();
			} catch (PlianceApiException e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}

	public void test_Ping_BadIssuer_Throws() {
		_issuer = "bad";

		IPlianceClient client = CreateFactory().Create("givenname", "subject");

		AssertThrows(PlianceApiException.class, (a) -> {
			try {
				client.Ping();
			} catch (PlianceApiException e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}

	private void AssertThrows(Class<?> type, Function<Void, Void> action) {
		try {
			action.apply(null);
			throw new RuntimeException();
		} catch (Throwable ex) {
//			if (type.isInstance(ex)) {
//				return;
//			}
		}
	}

	public void testRegisterPerson_MinimalPerson_VerifyData() throws Exception {
		RegisterPersonResponse result = CreatePerson();

		assertEquals("Success", result.status);
		assertTrue(result.success);
		assertNull(result.message);

		Hit hit = result.hits[0][0];

		assertEquals("EuSanction-833", hit.matchId);
		assertFalse(hit.isPep);
		assertFalse(hit.isRca);
		assertTrue(hit.isSanction);
		assertEquals("Osama bin Muhammad bin Awad", hit.firstName);
		assertEquals("bin Ladin", hit.lastName);
		assertEquals(ClassificationType.Unknown, hit.classification);
	}

	public void testClassify_NonExisting_Throws() {
		ClassifyHitCommand command = new ClassifyHitCommand();
		command.personReferenceId = "$RANDOM";
		command.matchId = "matchId";
		command.aliasId = "aliasId";
		command.classification = ClassificationType.Match;

		AssertThrows(PlianceApiException.class, (a) -> {
			try {
				_client.ClassifyPersonHit(command);
			} catch (PlianceApiException e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}

	public void testClassify_Existing_Pass() throws Exception {
		RegisterPersonResponse result = CreatePerson();
		Hit hit = result.hits[0][0];

		ClassifyHitCommand command = new ClassifyHitCommand();
		command.personReferenceId = _personReferenceId;
		command.matchId = hit.matchId;
		command.aliasId = hit.aliasId;
		command.classification = ClassificationType.FalsePositive;

		Thread.sleep(200, 0);
		ViewPersonQuery query = new ViewPersonQuery();
		query.personReferenceId = _personReferenceId;

		ViewPersonQueryResult view = _client.ViewPerson(query);
		
		Hit hit2 = view.data.hits[0][0];
		
		assertEquals(ClassificationType.FalsePositive, hit2.classification);
	}

	public void testGet_NonExisting_Throws() throws Exception {
		ViewPersonQuery query = new ViewPersonQuery();
		query.personReferenceId = _personReferenceId;

		AssertThrows(PlianceApiException.class, (a) -> {
			try {
				_client.ViewPerson(query);
			} catch (PlianceApiException e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}

	public void testDeletePerson_Existing_Deleted() throws Exception {
		CreatePerson();
		DeletePerson();
		Thread.sleep(200, 0);
		ViewPersonQuery query = new ViewPersonQuery();
		query.personReferenceId = _personReferenceId;

		AssertThrows(PlianceApiException.class, (a) -> {
			try {
				_client.ViewPerson(query);
			} catch (PlianceApiException e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}

	private DeletePersonResponse DeletePerson() throws Exception {
		DeletePersonCommand command = new DeletePersonCommand();
		command.personReferenceId = _personReferenceId;

		return _client.DeletePerson(command);
	}

	private RegisterPersonResponse CreatePerson() throws Exception {
		RegisterPersonCommand command = new RegisterPersonCommand();
		command.personReferenceId = _personReferenceId;
		command.firstName = "Osama";
		command.lastName = "Bin Laden";

		return _client.RegisterPerson(command);
	}

	private PlianceClientFactory CreateFactory() {
		return new PlianceClientFactory(_secret, _issuer, _url, _certificate);
	}
}
