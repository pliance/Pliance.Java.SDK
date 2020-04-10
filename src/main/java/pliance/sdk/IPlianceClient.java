package pliance.sdk;

import pliance.sdk.contracts.*;
import pliance.sdk.exceptions.*;

public interface IPlianceClient {
    // @inject: interface
	ArchiveCompanyResponse archiveCompany(ArchiveCompanyCommand command) throws PlianceApiException;
	ArchivePersonResponse archivePerson(ArchivePersonCommand command) throws PlianceApiException;
	CompanyGraphBeneficiariesResult beneficiaries(CompanyGraphBeneficiariesQuery query) throws PlianceApiException;
	ClassifyCompanyHitResponse classifyCompanyHit(ClassifyCompanyHitCommand command) throws PlianceApiException;
	ClassifyPersonHitResponse classifyPersonHit(ClassifyPersonHitCommand command) throws PlianceApiException;
	DeleteCompanyResponse deleteCompany(DeleteCompanyCommand command) throws PlianceApiException;
	DeletePersonResponse deletePerson(DeletePersonCommand command) throws PlianceApiException;
	FeedQueryResult feed(FeedQuery query) throws PlianceApiException;
	ReportQueryResult getReport(ReportQuery query) throws PlianceApiException;
	WebhookQueryResult getWebhook(WebhookQuery query) throws PlianceApiException;
	PingResponse ping(PingQuery query) throws PlianceApiException;
	RegisterCompanyResponse registerCompany(RegisterCompanyCommand command) throws PlianceApiException;
	RegisterPersonResponse registerPerson(RegisterPersonCommand command) throws PlianceApiException;
	WebhookUpdateResponse saveWebhook(WebhookUpdateCommand command) throws PlianceApiException;
	CompanySearchQueryResult searchCompany(CompanySearchQuery query) throws PlianceApiException;
	PersonSearchQueryResult searchPerson(PersonSearchQuery query) throws PlianceApiException;
	UnarchiveCompanyResponse unarchiveCompany(UnarchiveCompanyCommand command) throws PlianceApiException;
	UnarchivePersonResponse unarchivePerson(UnarchivePersonCommand command) throws PlianceApiException;
	ViewCompanyQueryResult viewCompany(ViewCompanyQuery query) throws PlianceApiException;
	ViewPersonQueryResult viewPerson(ViewPersonQuery query) throws PlianceApiException;
	WatchlistCompanyQueryResult watchlistCompany(WatchlistCompanyQuery query) throws PlianceApiException;
	WatchlistQueryResult watchlistPerson(WatchlistQuery query) throws PlianceApiException;
	WatchlistQueryResult_v2 watchlistPersonV2(WatchlistQuery_v2 query) throws PlianceApiException;

   // @inject: !interface
}
