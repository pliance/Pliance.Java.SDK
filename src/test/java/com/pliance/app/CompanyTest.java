
package com.pliance.app;

import java.util.UUID;

import junit.framework.Test;
import junit.framework.TestSuite;
import pliance.sdk.contracts.*;

public class CompanyTest extends TestBase {
	private String _name;
	
	public CompanyTest() {
		_name = "A Company AB";
	}

	public static Test suite() {
		return new TestSuite(CompanyTest.class);
	}
	
	public void test_Ping() throws Exception {
		_client.Ping();
	}

	public void test_Create() throws Exception {
		CreateCompany();

		Thread.sleep(200, 0);
		assertNotNull(ViewCompany().data);
	}

	public void test_Delete() throws Exception {
		CreateCompany();
		DeleteCompany();

		Thread.sleep(200, 0);
		assertNull(ViewCompany().data);
	}

	public void test_Archive() throws Exception {
		CreateCompany();
		ArchiveCompany();

		Thread.sleep(200, 0);
		assertTrue(ViewCompany().data.archived);
	}

	public void test_Unarchive() throws Exception {
		CreateCompany();
		ArchiveCompany();
		UnarchiveCompany();

		Thread.sleep(200, 0);
		assertFalse(ViewCompany().data.archived);
	}

	public void test_SearchCompany() throws Exception {
		_name = UUID.randomUUID().toString();
		CreateCompany();
		
		Thread.sleep(200, 0);
		SearchCompany();
	}

	private RegisterCompanyResponse CreateCompany() throws Exception {
		RegisterCompanyCommand command = new RegisterCompanyCommand();
		command.companyReferenceId = _referenceId;
		command.name = _name;
		command.identity = new CompanyIdentity();
		command.identity.country = "se";
		command.identity.identity = _referenceId;

		return _client.RegisterCompany(command);
	}

	private DeleteCompanyResponse DeleteCompany() throws Exception {
		DeleteCompanyCommand command = new DeleteCompanyCommand();
		command.companyReferenceId = _referenceId;

		return _client.DeleteCompany(command);
	}

	private ArchiveCompanyResponse ArchiveCompany() throws Exception {
		ArchiveCompanyCommand command = new ArchiveCompanyCommand();
		command.companyReferenceId = _referenceId;

		return _client.ArchiveCompany(command);
	}

	private UnarchiveCompanyResponse UnarchiveCompany() throws Exception {
		UnarchiveCompanyCommand command = new UnarchiveCompanyCommand();
		command.companyReferenceId = _referenceId;

		return _client.UnarchiveCompany(command);
	}

	private ViewCompanyQueryResult ViewCompany() throws Exception {
		ViewCompanyQuery query = new ViewCompanyQuery();
		query.companyReferenceId = _referenceId;

		return _client.ViewCompany(query);
	}
	
	private CompanySearchQueryResult SearchCompany() throws Exception {
		CompanySearchQuery query = new CompanySearchQuery();
		query.query = _name;

		return _client.SearchCompany(query);
	}	
}
