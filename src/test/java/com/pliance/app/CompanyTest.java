
package com.pliance.app;

import java.util.UUID;

import junit.framework.Test;
import junit.framework.TestSuite;
import pliance.sdk.contracts.*;
import pliance.sdk.exceptions.PlianceApiException;

public class CompanyTest extends TestBase {
	private String _name;
	
	public CompanyTest() throws Exception {
		_name = "A Company AB";
	}

	public static Test suite() {
		return new TestSuite(CompanyTest.class);
	}
	
	public void test_Ping() throws Exception {
		PingQuery query = new PingQuery();
		
		_client.ping(query);
	}

	public void test_Create() throws Exception {
		createCompany();

		assertNotNull(viewCompany().data);
	}

	public void test_Delete() throws Exception {
		createCompany();
		deleteCompany();

		Sync();
		assertThrows(PlianceApiException.class, () -> {
			viewCompany();
		});
	}

	public void test_Archive() throws Exception {
		createCompany();
		archiveCompany();

		Sync();
		assertTrue(viewCompany().data.archived);
	}

	public void test_Unarchive() throws Exception {
		createCompany();
		archiveCompany();
		unarchiveCompany();

		Sync();
		assertFalse(viewCompany().data.archived);
	}

	public void test_SearchCompany() throws Exception {
		_name = UUID.randomUUID().toString();
		createCompany();
		
		Sync();
		CompanySearchQueryResult result = searchCompany();
		assertEquals(1, result.data.result.length);		
	}

	private RegisterCompanyResponse createCompany() throws Exception {
		RegisterCompanyCommand command = new RegisterCompanyCommand();
		command.companyReferenceId = _referenceId;
		command.name = _name;
		command.identity = new CompanyIdentity();
		command.identity.country = "se";
		command.identity.identity = _referenceId;

		return _client.registerCompany(command);
	}

	private DeleteCompanyResponse deleteCompany() throws Exception {
		DeleteCompanyCommand command = new DeleteCompanyCommand();
		command.companyReferenceId = _referenceId;

		return _client.deleteCompany(command);
	}

	private ArchiveCompanyResponse archiveCompany() throws Exception {
		ArchiveCompanyCommand command = new ArchiveCompanyCommand();
		command.companyReferenceId = _referenceId;

		return _client.archiveCompany(command);
	}

	private UnarchiveCompanyResponse unarchiveCompany() throws Exception {
		UnarchiveCompanyCommand command = new UnarchiveCompanyCommand();
		command.companyReferenceId = _referenceId;

		return _client.unarchiveCompany(command);
	}

	private ViewCompanyQueryResult viewCompany() throws Exception {
		ViewCompanyQuery query = new ViewCompanyQuery();
		query.companyReferenceId = _referenceId;

		return _client.viewCompany(query);
	}
	
	private CompanySearchQueryResult searchCompany() throws Exception {
		CompanySearchQuery query = new CompanySearchQuery();
		query.query = _name;

		return _client.searchCompany(query);
	}	
}
