
package com.pliance.app;

import java.util.UUID;

import junit.framework.Test;
import junit.framework.TestSuite;
import pliance.sdk.contracts.*;
import pliance.sdk.exceptions.PlianceApiException;

public class CompanyTest extends TestBase {
	private String _name;
	
	public CompanyTest() throws Exception {
		_name = "Korea Daesong Bank";
	}

	public static Test suite() {
		return new TestSuite(CompanyTest.class);
	}

	public void test_createCompany() throws Exception {
		RegisterCompanyResponse response = createCompany();

		assertTrue(response.success);
	}

	public void test_archiveCompany() throws Exception {
		createCompany();
		ArchiveCompanyResponse response = archiveCompany();

		assertTrue(response.success);
	}

	public void test_unarchiveCompany() throws Exception {
		createCompany();
		archiveCompany();
		UnarchiveCompanyCommand command = new UnarchiveCompanyCommand();
		command.companyReferenceId = _id;

		UnarchiveCompanyResponse response = _client.unarchiveCompany(command);

		assertTrue(response.success);
	}

	public void test_deleteCompany() throws Exception {
		createCompany();
		DeleteCompanyCommand command = new DeleteCompanyCommand();
		command.companyReferenceId = _id;

		DeleteCompanyResponse response = _client.deleteCompany(command);

		assertTrue(response.success);
	}

	public void test_viewCompany() throws Exception {
		createCompany();
		ViewCompanyQuery command = new ViewCompanyQuery();
		command.companyReferenceId = _id;

		ViewCompanyQueryResult response = _client.viewCompany(command);

		assertTrue(response.success);
	}

	public void test_searchCompany() throws Exception {
		createCompany();
		CompanySearchQuery command = new CompanySearchQuery();
		command.query = "Daesong";

		CompanySearchQueryResult response = _client.searchCompany(command);

		assertTrue(response.success);
	}

	public void test_classifyCompany() throws Exception {
		RegisterCompanyResponse company = createCompany();
		CompanyHit match = company.data.hits[0][0];
		ClassifyCompanyHitCommand command = new ClassifyCompanyHitCommand();
		command.aliasId = match.aliasId;
		command.matchId = match.matchId;
		command.companyReferenceId = _id;
		command.classification = ClassificationType.FalsePositive;

		ClassifyCompanyHitResponse response = _client.classifyCompanyHit(command);

		assertTrue(response.success);
	}

	public void test_WatchlistCompany() throws Exception {
		RegisterCompanyResponse company = createCompany();
		CompanyHit match = company.data.hits[0][0];
		WatchlistCompanyQuery command = new WatchlistCompanyQuery();
		command.matchId = match.matchId;
		command.companyReferenceId = _id;

		WatchlistCompanyQueryResult response = _client.watchlistCompany(command);

		assertTrue(response.success);
	}

	private RegisterCompanyResponse createCompany() throws Exception {
		RegisterCompanyCommand command = new RegisterCompanyCommand();
		command.companyReferenceId = _id;
		command.name = _name;

		return _client.registerCompany(command);
	}

	private ArchiveCompanyResponse archiveCompany() throws Exception {
		ArchiveCompanyCommand command = new ArchiveCompanyCommand();
		command.companyReferenceId = _id;

		return _client.archiveCompany(command);
	}	
}
