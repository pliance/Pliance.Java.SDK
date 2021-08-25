package pliance.sdk;

import pliance.sdk.contracts.*;
import pliance.sdk.exceptions.*;

public interface IPlianceClient {
    // @inject: interface
	ArchiveCompanyResponse archiveCompany(ArchiveCompanyCommand command) throws PlianceApiException;
	ArchivePersonResponse archivePerson(ArchivePersonCommand command) throws PlianceApiException;
	ClassifyCompanyHitResponse classifyCompanyHit(ClassifyCompanyHitCommand command) throws PlianceApiException;
	ClassifyPersonHitResponse classifyPersonHit(ClassifyPersonHitCommand command) throws PlianceApiException;
	DeleteCompanyResponse deleteCompany(DeleteCompanyCommand command) throws PlianceApiException;
	DeletePersonResponse deletePerson(DeletePersonCommand command) throws PlianceApiException;
	FeedQueryResult feed(FeedQuery request) throws PlianceApiException;
	CompanyReportQueryResult getCompanyReport(CompanyReportQuery request) throws PlianceApiException;
	GeneralReportQueryResult getGeneralReport(GeneralReportQuery request) throws PlianceApiException;
	PersonReportQueryResult getPersonReport(PersonReportQuery request) throws PlianceApiException;
	WebhookQueryResult getWebhook(WebhookQuery request) throws PlianceApiException;
	ViewCompanyOwnershipQueryResult ownership(ViewCompanyOwnershipQuery request) throws PlianceApiException;
	PingResponse ping(PingQuery request) throws PlianceApiException;
	RegisterCompanyResponse registerCompany(RegisterCompanyCommand command) throws PlianceApiException;
	RegisterPersonResponse registerPerson(RegisterPersonCommand command) throws PlianceApiException;
	WebhookUpdateResponse saveWebhook(WebhookUpdateCommand command) throws PlianceApiException;
	CompanySearchQueryResult searchCompany(CompanySearchQuery request) throws PlianceApiException;
	PersonSearchQueryResult searchPerson(PersonSearchQuery request) throws PlianceApiException;
	UnarchiveCompanyResponse unarchiveCompany(UnarchiveCompanyCommand command) throws PlianceApiException;
	UnarchivePersonResponse unarchivePerson(UnarchivePersonCommand command) throws PlianceApiException;
	ViewCompanyQueryResult viewCompany(ViewCompanyQuery request) throws PlianceApiException;
	ViewPersonQueryResult viewPerson(ViewPersonQuery request) throws PlianceApiException;
	WatchlistCompanyQueryResult watchlistCompany(WatchlistCompanyQuery request) throws PlianceApiException;
	WatchlistQueryResult watchlistPerson(WatchlistQuery request) throws PlianceApiException;
	WatchlistQueryResultV2 watchlistPersonV2(WatchlistQueryV2 request) throws PlianceApiException;
    // @inject: !interface
}
