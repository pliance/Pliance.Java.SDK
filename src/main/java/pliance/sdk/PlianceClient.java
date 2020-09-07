package pliance.sdk;

import com.google.gson.Gson;
import pliance.sdk.contracts.*;
import pliance.sdk.contracts.responses.Response;
import pliance.sdk.exceptions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class PlianceClient implements IPlianceClient {
	private PlianceClientFactory _factory;
	private String _givenName;
	private String _subject;

	public PlianceClient(PlianceClientFactory factory, String givenName, String subject) {
		_subject = subject;
		_givenName = givenName;
		_factory = factory;
	}

	private <T> T execute(String method, String path, Func<HttpURLConnection, T, Exception> action)
			throws PlianceApiException {
		return _factory.execute(method, action, path, _givenName, _subject);
	}

	private String convert(InputStream inputStream) throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
		String line = null;

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
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

		String response = convert(client.getInputStream());
		Gson gson = new Gson();
		T result = gson.fromJson(response, type);

		if (!result.success) {
			throw new Exception("bad result");
		}

		return result;
	}

	private <T> void writePayload(HttpURLConnection client, T json) throws Exception {
		java.io.OutputStream stream = client.getOutputStream();
		Gson gson = new Gson();

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

	public FeedQueryResult feed(FeedQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/FeedQuery" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, FeedQueryResult.class);
		});
	}

	public ReportQueryResult getReport(ReportQuery request) throws PlianceApiException
	{
		if (request == null)
		{
			throw new ArgumentNullException("request");
		}

		return execute("GET", "api/ReportQuery" + UrlParameterEncoder.encode(request), (client) -> {
			return handleResponse(client, ReportQueryResult.class);
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
