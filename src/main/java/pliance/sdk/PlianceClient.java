package pliance.sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pliance.sdk.contracts.*;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.exceptions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class PlianceClient implements IPlianceClient {
	private PlianceClientFactory _factory;
	private String _givenName;
	private String _subject;

	public PlianceClient(PlianceClientFactory factory, String givenName, String subject) {
		_subject = subject;
		_givenName = givenName;
		_factory = factory;
	}
	
	private Gson BuildGson()
	{
		return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").setPrettyPrinting().create();
	}

	private <T> T execute(String method, String path, Func<HttpURLConnection, T, Exception> action)
			throws PlianceApiException {
		return _factory.execute(method, action, path, _givenName, _subject);
	}

	private String convert(InputStreamReader inputStream) throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
		String line = null;

		try (BufferedReader bufferedReader = new BufferedReader(inputStream)) {
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		}

		return stringBuilder.toString();
	}

	private <T extends Response> T handleResponse(HttpURLConnection client, Class<T> type) throws Exception {
		if (client.getResponseCode() != 200) {
			throw new Exception(
					"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
		}

		InputStream input = client.getInputStream();
		java.io.InputStreamReader stream = new java.io.InputStreamReader(input, StandardCharsets.UTF_8);
		String response = convert(stream);
		Gson gson = BuildGson();
		T result = gson.fromJson(response, type);

		if (!result.success) {
			throw new Exception("bad result");
		}

		return result;
	}

	private <T> void writePayload(HttpURLConnection client, T json) throws Exception {
		java.io.OutputStream stream = client.getOutputStream();
		Gson gson = BuildGson();

		stream.write(gson.toJson(json).getBytes("UTF-8"));
		stream.flush();
		stream.close();
	}

    // @inject: methods
	public ArchiveCompanyResponse archiveCompany(ArchiveCompanyCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("POST", "api/CompanyCommand/Archive", (client) -> {
			writePayload(client, command);
			return handleResponse(client, ArchiveCompanyResponse.class);
		});
	}

	public ArchiveCompanyResponse archiveCompanyV2(ArchiveCompanyCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("POST", "api/CompanyV2Command/Archive", (client) -> {
			writePayload(client, command);
			return handleResponse(client, ArchiveCompanyResponse.class);
		});
	}

	public ArchivePersonResponse archivePerson(ArchivePersonCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("POST", "api/PersonCommand/Archive", (client) -> {
			writePayload(client, command);
			return handleResponse(client, ArchivePersonResponse.class);
		});
	}

	public BatchRegisterPersonResponse batchRegisterPerson(BatchRegisterPersonCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("PUT", "api/PersonCommand/Batch", (client) -> {
			writePayload(client, command);
			return handleResponse(client, BatchRegisterPersonResponse.class);
		});
	}

	public ClassifyCompanyHitResponse classifyCompanyHit(ClassifyCompanyHitCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("POST", "api/CompanyCommand/Classify", (client) -> {
			writePayload(client, command);
			return handleResponse(client, ClassifyCompanyHitResponse.class);
		});
	}

	public ClassifyCompanyLinkResponse classifyCompanyV2Link(ClassifyCompanyLinkCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("POST", "api/CompanyV2Command/ClassifyLink", (client) -> {
			writePayload(client, command);
			return handleResponse(client, ClassifyCompanyLinkResponse.class);
		});
	}

	public ClassifyCompanyResponse classifyCompanyV2Match(ClassifyCompanyCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("POST", "api/CompanyV2Command/Classify", (client) -> {
			writePayload(client, command);
			return handleResponse(client, ClassifyCompanyResponse.class);
		});
	}

	public ClassifyPersonHitResponse classifyPersonHit(ClassifyPersonHitCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("POST", "api/PersonCommand/Classify", (client) -> {
			writePayload(client, command);
			return handleResponse(client, ClassifyPersonHitResponse.class);
		});
	}

	public ViewCompanyDataQueryResult companyData(ViewCompanyDataQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/CompanyQuery/CompanyData" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, ViewCompanyDataQueryResult.class);
		});
	}

	public DeleteCompanyResponse deleteCompany(DeleteCompanyCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("DELETE", "api/CompanyCommand" + UrlParameterEncoder.encode(command), (client) -> {
			return handleResponse(client, DeleteCompanyResponse.class);
		});
	}

	public DeleteCompanyResponse deleteCompanyV2(DeleteCompanyCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("DELETE", "api/CompanyV2Command" + UrlParameterEncoder.encode(command), (client) -> {
			return handleResponse(client, DeleteCompanyResponse.class);
		});
	}

	public DeletePersonResponse deletePerson(DeletePersonCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("DELETE", "api/PersonCommand" + UrlParameterEncoder.encode(command), (client) -> {
			return handleResponse(client, DeletePersonResponse.class);
		});
	}

	public WebhookDeleteResponse deleteWebhookDeliveryFailure(WebhookDeleteCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("DELETE", "api/WebhookCommand" + UrlParameterEncoder.encode(command), (client) -> {
			return handleResponse(client, WebhookDeleteResponse.class);
		});
	}

	public CompanyReportQueryResult getCompanyReport(CompanyReportQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/ReportQuery/CompanyReport" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, CompanyReportQueryResult.class);
		});
	}

	public GeneralReportQueryResult getGeneralReport(GeneralReportQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/ReportQuery/GeneralReport" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, GeneralReportQueryResult.class);
		});
	}

	public PersonReportQueryResult getPersonReport(PersonReportQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/ReportQuery/PersonReport" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, PersonReportQueryResult.class);
		});
	}

	public WebhookQueryResult getWebhook(WebhookQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/WebhookQuery" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, WebhookQueryResult.class);
		});
	}

	public ListCompanyQueryResult listCompanies(ListCompanyQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/CompanyQuery/List" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, ListCompanyQueryResult.class);
		});
	}

	public ListCompanyQueryResult listCompaniesV2(ListCompanyV2Query query) throws PlianceApiException
	{
		if (query == null)
		{
			throw new ArgumentNullException("query");
		}

		return execute("GET", "api/CompanyV2Query/List" + UrlParameterEncoder.encode(query), (client) -> {
			return handleResponse(client, ListCompanyQueryResult.class);
		});
	}

	public ListPersonQueryResult listPersons(ListPersonQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/PersonQuery/List" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, ListPersonQueryResult.class);
		});
	}

	public WebhookDeliveryFailuresQueryResult listWebhookDeliveryFailures(WebhookDeliveryFailuresQuery query) throws PlianceApiException
	{
		if (query == null)
		{
			throw new ArgumentNullException("query");
		}

		return execute("GET", "api/WebhookQuery/DeliveryFailures" + UrlParameterEncoder.encode(query), (client) -> {
			return handleResponse(client, WebhookDeliveryFailuresQueryResult.class);
		});
	}

	public PingResponse ping(PingQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/Ping" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, PingResponse.class);
		});
	}

	public WebhookPokeQueryResult poke(WebhookPokeQuery query) throws PlianceApiException
	{
		if (query == null)
		{
			throw new ArgumentNullException("query");
		}

		return execute("POST", "api/WebhookQuery/Poke", (client) -> {
			writePayload(client, query);
			return handleResponse(client, WebhookPokeQueryResult.class);
		});
	}

	public RegisterCompanyResponse registerCompany(RegisterCompanyCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("PUT", "api/CompanyCommand", (client) -> {
			writePayload(client, command);
			return handleResponse(client, RegisterCompanyResponse.class);
		});
	}

	public RegisterCompanyV2Response registerCompanyV2(RegisterCompanyV2Command command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("PUT", "api/CompanyV2Command", (client) -> {
			writePayload(client, command);
			return handleResponse(client, RegisterCompanyV2Response.class);
		});
	}

	public RegisterPersonResponse registerPerson(RegisterPersonCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("PUT", "api/PersonCommand", (client) -> {
			writePayload(client, command);
			return handleResponse(client, RegisterPersonResponse.class);
		});
	}

	public WebhookUpdateResponse saveWebhook(WebhookUpdateCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("PUT", "api/WebhookCommand", (client) -> {
			writePayload(client, command);
			return handleResponse(client, WebhookUpdateResponse.class);
		});
	}

	public CompanySearchQueryResult searchCompany(CompanySearchQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/CompanyQuery/Search" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, CompanySearchQueryResult.class);
		});
	}

	public PersonSearchQueryResult searchPerson(PersonSearchQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/PersonQuery/Search" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, PersonSearchQueryResult.class);
		});
	}

	public UnarchiveCompanyResponse unarchiveCompany(UnarchiveCompanyCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("POST", "api/CompanyCommand/Unarchive", (client) -> {
			writePayload(client, command);
			return handleResponse(client, UnarchiveCompanyResponse.class);
		});
	}

	public UnarchiveCompanyResponse unarchiveCompanyV2(UnarchiveCompanyCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("POST", "api/CompanyV2Command/Unarchive", (client) -> {
			writePayload(client, command);
			return handleResponse(client, UnarchiveCompanyResponse.class);
		});
	}

	public UnarchivePersonResponse unarchivePerson(UnarchivePersonCommand command) throws PlianceApiException
	{
		if (command == null)
		{
			throw new ArgumentNullException("command");
		}

		return execute("POST", "api/PersonCommand/Unarchive", (client) -> {
			writePayload(client, command);
			return handleResponse(client, UnarchivePersonResponse.class);
		});
	}

	public ViewCompanyQueryResult viewCompany(ViewCompanyQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/CompanyQuery" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, ViewCompanyQueryResult.class);
		});
	}

	public ViewCompanyV2Response viewCompanyV2(ViewCompanyQuery query) throws PlianceApiException
	{
		if (query == null)
		{
			throw new ArgumentNullException("query");
		}

		return execute("GET", "api/CompanyV2Query" + UrlParameterEncoder.encode(query), (client) -> {
			return handleResponse(client, ViewCompanyV2Response.class);
		});
	}

	public ViewPersonQueryResult viewPerson(ViewPersonQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/PersonQuery" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, ViewPersonQueryResult.class);
		});
	}

	public WatchlistCompanyQueryResult watchlistCompany(WatchlistCompanyQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/WatchlistQuery/Company" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, WatchlistCompanyQueryResult.class);
		});
	}

	public WatchlistCompanyQueryResult watchlistCompanyV2(WatchlistCompanyV2Query request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/WatchlistQuery/CompanyV2" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, WatchlistCompanyQueryResult.class);
		});
	}

	public WatchlistQueryResultV2 watchlistCompanyV2Link(WatchlistCompanyV2LinkQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/WatchlistQuery/CompanyV2Link" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, WatchlistQueryResultV2.class);
		});
	}

	public WatchlistQueryResult watchlistPerson(WatchlistQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/WatchlistQuery" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, WatchlistQueryResult.class);
		});
	}

	public WatchlistQueryResultV2 watchlistPersonV2(WatchlistQueryV2 request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/WatchlistQuery/v2" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, WatchlistQueryResultV2.class);
		});
	}

    // @inject: !methods
}
