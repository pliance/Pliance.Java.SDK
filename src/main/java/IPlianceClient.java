import Contracts.*;

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
