package pliance.sdk;
import pliance.sdk.Contracts.*;
import pliance.sdk.Exceptions.PlianceApiException;

public interface IPlianceClient {
	RegisterPersonResponse RegisterPerson(RegisterPersonCommand command) throws Exception;

	ArchivePersonResponse ArchivePerson(ArchivePersonCommand command) throws Exception;

	DeletePersonResponse DeletePerson(DeletePersonCommand command) throws Exception;

	ClassifyHitResponse ClassifyPersonHit(ClassifyHitCommand command) throws Exception;

	PersonSearchQueryResult SearchPerson(PersonSearchQuery query) throws Exception;

	ViewPersonQueryResult ViewPerson(ViewPersonQuery query) throws Exception;

	PingResponse Ping() throws Exception;

	RegisterCompanyResponse RegisterCompany(RegisterCompanyCommand command);

	DeleteCompanyResponse DeleteCompany(DeleteCompanyCommand command);

	ArchiveCompanyResponse ArchiveCompany(ArchiveCompanyCommand command);

	CompanySearchQueryResult SearchCompany(CompanySearchQuery request);

	ViewCompanyQueryResult ViewCompany(ViewCompanyQuery request);
}
