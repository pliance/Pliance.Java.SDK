package pliance.sdk;
import pliance.sdk.Contracts.*;
import pliance.sdk.Contracts.ArchiveCompanyCommand;
import pliance.sdk.Contracts.ArchiveCompanyResponse;
import pliance.sdk.Contracts.ArchivePersonCommand;
import pliance.sdk.Contracts.ArchivePersonResponse;
import pliance.sdk.Contracts.ClassifyHitCommand;
import pliance.sdk.Contracts.ClassifyHitResponse;
import pliance.sdk.Contracts.CompanySearchQuery;
import pliance.sdk.Contracts.CompanySearchQueryResult;
import pliance.sdk.Contracts.DeleteCompanyCommand;
import pliance.sdk.Contracts.DeleteCompanyResponse;
import pliance.sdk.Contracts.DeletePersonCommand;
import pliance.sdk.Contracts.DeletePersonResponse;
import pliance.sdk.Contracts.PersonSearchQuery;
import pliance.sdk.Contracts.PersonSearchQueryResult;
import pliance.sdk.Contracts.RegisterCompanyCommand;
import pliance.sdk.Contracts.RegisterCompanyResponse;
import pliance.sdk.Contracts.RegisterPersonCommand;
import pliance.sdk.Contracts.RegisterPersonResponse;
import pliance.sdk.Contracts.ViewCompanyQuery;
import pliance.sdk.Contracts.ViewCompanyQueryResult;
import pliance.sdk.Contracts.ViewPersonQuery;
import pliance.sdk.Contracts.ViewPersonQueryResult;

public interface IPlianceClient {
	RegisterPersonResponse RegisterPerson(RegisterPersonCommand command) throws Exception;

	ArchivePersonResponse ArchivePerson(ArchivePersonCommand command);

	DeletePersonResponse DeletePerson(DeletePersonCommand command);

	ClassifyHitResponse ClassifyPersonHit(ClassifyHitCommand command);

	PersonSearchQueryResult SearchPerson(PersonSearchQuery query);

	ViewPersonQueryResult ViewPerson(ViewPersonQuery query);

	void Ping();

	RegisterCompanyResponse RegisterCompany(RegisterCompanyCommand command);

	DeleteCompanyResponse DeleteCompany(DeleteCompanyCommand command);

	ArchiveCompanyResponse ArchiveCompany(ArchiveCompanyCommand command);

	CompanySearchQueryResult SearchCompany(CompanySearchQuery request);

	ViewCompanyQueryResult ViewCompany(ViewCompanyQuery request);
}
