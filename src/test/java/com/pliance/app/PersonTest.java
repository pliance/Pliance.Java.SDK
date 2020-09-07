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

	public void test_Id() throws Exception {
		System.out.println(_referenceId);
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
		command.personReferenceId = _referenceId;

		UnarchivePersonResponse response = _client.unarchivePerson(command);		

		assertTrue(response.success);
	}

	public void test_deletePerson() throws Exception {
		createPerson();
		DeletePersonCommand command = new DeletePersonCommand();
		command.personReferenceId = _referenceId;

		DeletePersonResponse response = _client.deletePerson(command);		

		assertTrue(response.success);
	}	

	public void test_viewPerson() throws Exception {
		createPerson();
		ViewPersonQuery command = new ViewPersonQuery();
		command.personReferenceId = _referenceId;

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
		command.personReferenceId = _referenceId;
		command.classification = ClassificationType.FalsePositive;

		ClassifyPersonHitResponse response = _client.classifyPersonHit(command);

		assertTrue(response.success);
	}		

	// public void test_Delete() throws Exception {
	// 	createPerson();
	// 	deletePerson();
	// }

	// public void test_Archive() throws Exception {
	// 	createPerson();
	// 	ArchivePerson();

	// 	Sync();
	// 	assertTrue(viewPerson().data.archived);
	// }

	// public void test_Unarchive() throws Exception {
	// 	createPerson();
	// 	ArchivePerson();
	// 	unarchivePerson();

	// 	Sync();
	// 	assertFalse(viewPerson().data.archived);
	// }

	// public void test_SearchPerson() throws Exception {
	// 	_firstName = UUID.randomUUID().toString();
	// 	createPerson();

	// 	Sync();
	// 	PersonSearchQueryResult result = searchPerson();
	// 	assertEquals(1, result.data.result.length);
	// }

	// public void test_Classify() throws Exception {
	// 	RegisterPersonResponse result = createPerson();
	// 	PersonDetailsHitModel hit = result.data.hits[0][0];

	// 	ClassifyPersonHitCommand command = new ClassifyPersonHitCommand();
	// 	command.personReferenceId = _referenceId;
	// 	command.matchId = hit.matchId;
	// 	command.aliasId = hit.aliasId;
	// 	command.classification = ClassificationType.FalsePositive;
	// 	_client.classifyPersonHit(command);

	// 	Sync();
	// 	ViewPersonQuery query = new ViewPersonQuery();
	// 	query.personReferenceId = _referenceId;

	// 	ViewPersonQueryResult view = _client.viewPerson(query);

	// 	assertEquals(ClassificationType.FalsePositive, view.data.hits[0][0].classification);
	// }

	private RegisterPersonResponse createPerson() throws Exception {
		RegisterPersonCommand command = new RegisterPersonCommand();
		command.personReferenceId = _referenceId;
		command.firstName = _firstName;
		command.lastName = _lastName;

		return _client.registerPerson(command);
	}

	private ArchivePersonResponse archivePerson() throws Exception {
		ArchivePersonCommand command = new ArchivePersonCommand();
		command.personReferenceId = _referenceId;

		return _client.archivePerson(command);
	}

	// private DeletePersonResponse deletePerson() throws Exception {
	// 	DeletePersonCommand command = new DeletePersonCommand();
	// 	command.personReferenceId = _referenceId;

	// 	return _client.deletePerson(command);
	// }

	// private ArchivePersonResponse ArchivePerson() throws Exception {
	// 	ArchivePersonCommand command = new ArchivePersonCommand();
	// 	command.personReferenceId = _referenceId;

	// 	return _client.archivePerson(command);
	// }

	// private UnarchivePersonResponse unarchivePerson() throws Exception {
	// 	UnarchivePersonCommand command = new UnarchivePersonCommand();
	// 	command.personReferenceId = _referenceId;

	// 	return _client.unarchivePerson(command);
	// }

	// private ViewPersonQueryResult viewPerson() throws Exception {
	// 	ViewPersonQuery query = new ViewPersonQuery();
	// 	query.personReferenceId = _referenceId;

	// 	return _client.viewPerson(query);
	// }

	// private PersonSearchQueryResult searchPerson() throws Exception {
	// 	PersonSearchQuery query = new PersonSearchQuery();
	// 	query.query = _firstName;

	// 	return _client.searchPerson(query);
	// }
}
