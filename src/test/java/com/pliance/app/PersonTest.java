package com.pliance.app;

import java.util.UUID;
import java.util.function.Function;

import junit.framework.Test;
import junit.framework.TestSuite;
import pliance.sdk.contracts.*;

public class PersonTest extends TestBase {
	private String _firstName;
	private String _lastName;

	public static Test suite() {
		return new TestSuite(PersonTest.class);
	}

	public PersonTest() {
		_firstName = "Osama";
		_lastName = "Bin Laden";
	}

	public void test_Ping() throws Exception {
		_client.Ping();
	}

	public void test_Create() throws Exception {
		CreatePerson();

		Thread.sleep(200, 0);
		assertNotNull(ViewPerson().data);
	}

	public void test_Delete() throws Exception {
		System.out.println(_referenceId);
		CreatePerson();
		DeletePerson();

		Thread.sleep(200, 0);
		AssertThrows(() -> {
			ViewPerson();
		});
	}

	public void test_Archive() throws Exception {
		CreatePerson();
		ArchivePerson();

		Thread.sleep(200, 0);
		assertTrue(ViewPerson().data.archived);
	}

	public void test_Unarchive() throws Exception {
		CreatePerson();
		ArchivePerson();
		UnarchivePerson();

		Thread.sleep(200, 0);
		assertFalse(ViewPerson().data.archived);
	}

	public void test_SearchPerson() throws Exception {
		_firstName = UUID.randomUUID().toString();
		CreatePerson();

		Thread.sleep(200, 0);
		SearchPerson();
	}

	public void testClassify() throws Exception {
		RegisterPersonResponse result = CreatePerson();
		Hit hit = result.hits[0][0];

		ClassifyHitCommand command = new ClassifyHitCommand();
		command.personReferenceId = _referenceId;
		command.matchId = hit.matchId;
		command.aliasId = hit.aliasId;
		command.classification = ClassificationType.FalsePositive;
		_client.ClassifyPersonHit(command);

		Thread.sleep(200, 0);
		ViewPersonQuery query = new ViewPersonQuery();
		query.personReferenceId = _referenceId;

		ViewPersonQueryResult view = _client.ViewPerson(query);

		Hit hit2 = view.data.hits[0][0];

		assertEquals(ClassificationType.FalsePositive, hit2.classification);
	}

	private RegisterPersonResponse CreatePerson() throws Exception {
		RegisterPersonCommand command = new RegisterPersonCommand();
		command.personReferenceId = _referenceId;
		command.firstName = _firstName;
		command.lastName = _lastName;

		return _client.RegisterPerson(command);
	}

	private DeletePersonResponse DeletePerson() throws Exception {
		DeletePersonCommand command = new DeletePersonCommand();
		command.personReferenceId = _referenceId;

		return _client.DeletePerson(command);
	}

	private ArchivePersonResponse ArchivePerson() throws Exception {
		ArchivePersonCommand command = new ArchivePersonCommand();
		command.personReferenceId = _referenceId;

		return _client.ArchivePerson(command);
	}

	private UnarchivePersonResponse UnarchivePerson() throws Exception {
		UnarchivePersonCommand command = new UnarchivePersonCommand();
		command.personReferenceId = _referenceId;

		return _client.UnarchivePerson(command);
	}

	private ViewPersonQueryResult ViewPerson() throws Exception {
		ViewPersonQuery query = new ViewPersonQuery();
		query.personReferenceId = _referenceId;

		return _client.ViewPerson(query);
	}

	private PersonSearchQueryResult SearchPerson() throws Exception {
		PersonSearchQuery query = new PersonSearchQuery();
		query.query = _firstName;

		return _client.SearchPerson(query);
	}
}
