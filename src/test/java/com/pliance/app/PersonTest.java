package com.pliance.app;

import java.util.UUID;
import junit.framework.Test;
import junit.framework.TestSuite;
import pliance.sdk.contracts.*;

public class PersonTest extends TestBase {
	private String _firstName;
	private String _lastName;

	public static Test suite() {
		return new TestSuite(PersonTest.class);
	}

	public PersonTest() throws Exception {
		_firstName = "Osama";
		_lastName = "bin Laden";
	}

	public void test_createPerson() throws Exception {
		RegisterPersonResponse response = createPerson();

		assertTrue(response.success);
	}

	public void test_archivePerson() throws Exception {
		createPerson();
		ArchivePersonResponse response = archivePerson();

		assertTrue(response.success);
	}

	public void test_unarchivePerson() throws Exception {
		createPerson();
		archivePerson();
		UnarchivePersonCommand command = new UnarchivePersonCommand();
		command.personReferenceId = _id;

		UnarchivePersonResponse response = _client.unarchivePerson(command);

		assertTrue(response.success);
	}

	public void test_deletePerson() throws Exception {
		createPerson();
		DeletePersonCommand command = new DeletePersonCommand();
		command.personReferenceId = _id;

		DeletePersonResponse response = _client.deletePerson(command);

		assertTrue(response.success);
	}

	public void test_viewPerson() throws Exception {
		createPerson();
		ViewPersonQuery command = new ViewPersonQuery();
		command.personReferenceId = _id;

		ViewPersonQueryResult response = _client.viewPerson(command);

		assertTrue(response.success);
	}

	public void test_searchPerson() throws Exception {
		createPerson();
		PersonSearchQuery command = new PersonSearchQuery();
		command.query = "Osama";

		PersonSearchQueryResult response = _client.searchPerson(command);

		assertTrue(response.success);
	}

	public void test_classifyPerson() throws Exception {
		RegisterPersonResponse person = createPerson();
		PersonDetailsHitModel match = person.data.hits[0][0];
		ClassifyPersonHitCommand command = new ClassifyPersonHitCommand();
		command.aliasId = match.aliasId;
		command.matchId = match.matchId;
		command.personReferenceId = _id;
		command.classification = ClassificationType.FalsePositive;

		ClassifyPersonHitResponse response = _client.classifyPersonHit(command);

		assertTrue(response.success);
	}

	public void test_WatchlistPersonV1() throws Exception {
		RegisterPersonResponse person = createPerson();
		PersonDetailsHitModel match = person.data.hits[0][0];
		WatchlistQuery command = new WatchlistQuery();
		command.id = match.matchId;
		command.firstName = "Osama";
		command.lastName = "bin Laden";

		WatchlistQueryResult response = _client.watchlistPerson(command);

		assertTrue(response.success);
	}

	public void test_WatchlistPersonV2() throws Exception {
		RegisterPersonResponse person = createPerson();
		PersonDetailsHitModel match = person.data.hits[0][0];
		WatchlistQueryV2 command = new WatchlistQueryV2();
		command.matchId = match.matchId;
		command.personReferenceId = _id;

		WatchlistQueryResultV2 response = _client.watchlistPersonV2(command);

		assertTrue(response.success);
	}

	private RegisterPersonResponse createPerson() throws Exception {
		RegisterPersonCommand command = new RegisterPersonCommand();
		command.personReferenceId = _id;
		command.firstName = _firstName;
		command.lastName = _lastName;

		return _client.registerPerson(command);
	}

	private ArchivePersonResponse archivePerson() throws Exception {
		ArchivePersonCommand command = new ArchivePersonCommand();
		command.personReferenceId = _id;

		return _client.archivePerson(command);
	}
}
