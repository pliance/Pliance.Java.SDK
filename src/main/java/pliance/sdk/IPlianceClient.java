package pliance.sdk;

import pliance.sdk.contracts.*;
import pliance.sdk.exceptions.*;

public interface IPlianceClient {
	String Source();

	RegisterPersonResponse RegisterPerson(RegisterPersonCommand command) throws Exception;

	ArchivePersonResponse ArchivePerson(ArchivePersonCommand command) throws Exception;

	UnarchivePersonResponse UnarchivePerson(UnarchivePersonCommand command) throws Exception;

	DeletePersonResponse DeletePerson(DeletePersonCommand command) throws Exception;

	ClassifyHitResponse ClassifyPersonHit(ClassifyHitCommand command) throws Exception;

	PersonSearchQueryResult SearchPerson(PersonSearchQuery query) throws Exception;

	ViewPersonQueryResult ViewPerson(ViewPersonQuery query) throws Exception;

	PingResponse Ping() throws Exception;

	RegisterCompanyResponse RegisterCompany(RegisterCompanyCommand command) throws Exception;

	DeleteCompanyResponse DeleteCompany(DeleteCompanyCommand command) throws Exception;

	ArchiveCompanyResponse ArchiveCompany(ArchiveCompanyCommand command) throws Exception;

	UnarchiveCompanyResponse UnarchiveCompany(UnarchiveCompanyCommand command) throws Exception;

	CompanySearchQueryResult SearchCompany(CompanySearchQuery request) throws Exception;

	ViewCompanyQueryResult ViewCompany(ViewCompanyQuery request) throws Exception;
}
