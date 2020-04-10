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
		_lastName = "Bin Laden";
	}

	public void test_Create() throws Exception {
		RegisterPersonResponse response = createPerson();

		assertNotNull(response.data);
	}

	public void test_Delete() throws Exception {
		createPerson();
		deletePerson();
	}

	public void test_Archive() throws Exception {
		createPerson();
		ArchivePerson();

		Sync();
		assertTrue(viewPerson().data.archived);
	}

	public void test_Unarchive() throws Exception {
		createPerson();
		ArchivePerson();
		unarchivePerson();

		Sync();
		assertFalse(viewPerson().data.archived);
	}

	public void test_SearchPerson() throws Exception {
		_firstName = UUID.randomUUID().toString();
		createPerson();

		Sync();
		PersonSearchQueryResult result = searchPerson();
		assertEquals(1, result.data.result.length);
	}

	public void test_Classify() throws Exception {
		RegisterPersonResponse result = createPerson();
		PersonHit hit = result.data.hits[0][0];

		ClassifyPersonHitCommand command = new ClassifyPersonHitCommand();
		command.personReferenceId = _referenceId;
		command.matchId = hit.matchId;
		command.aliasId = hit.aliasId;
		command.classification = ClassificationType.FalsePositive;
		_client.classifyPersonHit(command);

		Sync();
		ViewPersonQuery query = new ViewPersonQuery();
		query.personReferenceId = _referenceId;

		ViewPersonQueryResult view = _client.viewPerson(query);

		assertEquals(ClassificationType.FalsePositive, view.data.hits[0][0].classification);
	}

	private RegisterPersonResponse createPerson() throws Exception {
		RegisterPersonCommand command = new RegisterPersonCommand();
		command.personReferenceId = _referenceId;
		command.firstName = _firstName;
		command.lastName = _lastName;

		return _client.registerPerson(command);
	}

	private DeletePersonResponse deletePerson() throws Exception {
		DeletePersonCommand command = new DeletePersonCommand();
		command.personReferenceId = _referenceId;

		return _client.deletePerson(command);
	}

	private ArchivePersonResponse ArchivePerson() throws Exception {
		ArchivePersonCommand command = new ArchivePersonCommand();
		command.personReferenceId = _referenceId;

		return _client.archivePerson(command);
	}

	private UnarchivePersonResponse unarchivePerson() throws Exception {
		UnarchivePersonCommand command = new UnarchivePersonCommand();
		command.personReferenceId = _referenceId;

		return _client.unarchivePerson(command);
	}

	private ViewPersonQueryResult viewPerson() throws Exception {
		ViewPersonQuery query = new ViewPersonQuery();
		query.personReferenceId = _referenceId;

		return _client.viewPerson(query);
	}

	private PersonSearchQueryResult searchPerson() throws Exception {
		PersonSearchQuery query = new PersonSearchQuery();
		query.query = _firstName;

		return _client.searchPerson(query);
	}
}
