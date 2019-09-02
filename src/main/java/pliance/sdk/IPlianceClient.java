package pliance.sdk;

import pliance.sdk.contracts.*;
import pliance.sdk.exceptions.*;

public interface IPlianceClient {
	String Source();

	RegisterPersonResponse RegisterPerson(RegisterPersonCommand command) throws PlianceApiException;

	ArchivePersonResponse ArchivePerson(ArchivePersonCommand command) throws PlianceApiException;

	UnarchivePersonResponse UnarchivePerson(UnarchivePersonCommand command) throws PlianceApiException;

	DeletePersonResponse DeletePerson(DeletePersonCommand command) throws PlianceApiException;

	ClassifyHitResponse ClassifyPersonHit(ClassifyHitCommand command) throws PlianceApiException;

	PersonSearchQueryResult SearchPerson(PersonSearchQuery query) throws PlianceApiException;

	ViewPersonQueryResult ViewPerson(ViewPersonQuery query) throws PlianceApiException;

	PingResponse Ping() throws PlianceApiException;

	RegisterCompanyResponse RegisterCompany(RegisterCompanyCommand command) throws PlianceApiException;

	DeleteCompanyResponse DeleteCompany(DeleteCompanyCommand command) throws PlianceApiException;

	ArchiveCompanyResponse ArchiveCompany(ArchiveCompanyCommand command) throws PlianceApiException;

	UnarchiveCompanyResponse UnarchiveCompany(UnarchiveCompanyCommand command) throws PlianceApiException;

	CompanySearchQueryResult SearchCompany(CompanySearchQuery request) throws PlianceApiException;

	ViewCompanyQueryResult ViewCompany(ViewCompanyQuery request) throws PlianceApiException;
}
