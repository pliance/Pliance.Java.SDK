package pliance.sdk;

import pliance.sdk.contracts.*;
import pliance.sdk.contracts.models.CompanySearchQueryResult;
import pliance.sdk.contracts.models.PersonSearchQueryResult;
import pliance.sdk.contracts.models.ViewCompanyQueryResult;
import pliance.sdk.contracts.models.ViewPersonQueryResult;
import pliance.sdk.exceptions.*;

public interface IPlianceClient {
	String Source();

	RegisterPersonResponse registerPerson(RegisterPersonCommand command) throws PlianceApiException;

	ArchivePersonResponse archivePerson(ArchivePersonCommand command) throws PlianceApiException;

	UnarchivePersonResponse unarchivePerson(UnarchivePersonCommand command) throws PlianceApiException;

	DeletePersonResponse deletePerson(DeletePersonCommand command) throws PlianceApiException;

	ClassifyPersonHitResponse classifyPersonHit(ClassifyPersonHitCommand command) throws PlianceApiException;

	PersonSearchQueryResult searchPerson(PersonSearchQuery query) throws PlianceApiException;

	ViewPersonQueryResult viewPerson(ViewPersonQuery query) throws PlianceApiException;

	PingResponse ping() throws PlianceApiException;

	RegisterCompanyResponse registerCompany(RegisterCompanyCommand command) throws PlianceApiException;

	DeleteCompanyResponse deleteCompany(DeleteCompanyCommand command) throws PlianceApiException;

	ArchiveCompanyResponse archiveCompany(ArchiveCompanyCommand command) throws PlianceApiException;

	UnarchiveCompanyResponse unarchiveCompany(UnarchiveCompanyCommand command) throws PlianceApiException;

	CompanySearchQueryResult searchCompany(CompanySearchQuery request) throws PlianceApiException;

	ViewCompanyQueryResult viewCompany(ViewCompanyQuery request) throws PlianceApiException;

	ClassifyCompanyHitResponse classifyCompanyHit(ClassifyCompanyHitCommand command) throws PlianceApiException;
}
