package pliance.sdk;

import com.google.gson.Gson;
import pliance.sdk.contracts.*;
import pliance.sdk.contracts.models.company.*;
import pliance.sdk.contracts.person.*;
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

	public RegisterPersonResponse registerPerson(RegisterPersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		return execute("PUT", "api/PersonCommand", (client) -> {
			writePayload(client, command);
			return handleResponse(client, RegisterPersonResponse.class);
		});
	}

	private <T extends Response> T handleResponse(HttpURLConnection client, Class<T> type) throws Exception {
		if (client.getResponseCode() != 200) {
			throw new HttpException(
					"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
		}

		String response = convert(client.getInputStream());
		Gson gson = new Gson();
		T result = gson.fromJson(response, type);

		if (!result.success) {
			throw new HttpException("bad result");
		}

		return result;
	}

	public ArchivePersonResponse archivePerson(ArchivePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		return execute("POST", "api/PersonCommand/Archive", (client) -> {
			writePayload(client, command);
			return handleResponse(client, ArchivePersonResponse.class);
		});
	}

	public UnarchivePersonResponse unarchivePerson(UnarchivePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		return execute("POST", "api/PersonCommand/Unarchive", (client) -> {
			writePayload(client, command);
			return handleResponse(client, UnarchivePersonResponse.class);
		});
	}

	public DeletePersonResponse deletePerson(DeletePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		return execute("DELETE", "api/PersonCommand" + UrlParameterEncoder.encode(command), (client) -> {
			return handleResponse(client, DeletePersonResponse.class);
		});
	}

	public ClassifyPersonHitResponse classifyPersonHit(ClassifyPersonHitCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		return execute("POST", "api/PersonCommand/Classify", (client) -> {
			writePayload(client, command);
			return handleResponse(client, ClassifyPersonHitResponse.class);
		});
	}

	private <T> void writePayload(HttpURLConnection client, T json) throws Exception {
		java.io.OutputStream stream = client.getOutputStream();
		Gson gson = new Gson();

		stream.write(gson.toJson(json).getBytes("UTF-8"));
		stream.flush();
		stream.close();
	}

	public PersonSearchQueryResult searchPerson(PersonSearchQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		return execute("GET", "api/PersonQuery/Search/" + UrlParameterEncoder.encode(query), (client) -> {
			return handleResponse(client, PersonSearchQueryResult.class);
		});
	}

	public ViewPersonQueryResult viewPerson(ViewPersonQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		return execute("GET", "api/PersonQuery/" + UrlParameterEncoder.encode(query), (client) -> {
			return handleResponse(client, ViewPersonQueryResult.class);
		});
	}

	public PingResponse ping() throws PlianceApiException {
		return execute("GET", "api/Ping", (client) -> {
			return handleResponse(client, PingResponse.class);
		});
	}

	public RegisterCompanyResponse registerCompany(RegisterCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		return execute("PUT", "api/CompanyCommand", (client) -> {
			writePayload(client, command);
			return handleResponse(client, RegisterCompanyResponse.class);
		});
	}

	public DeleteCompanyResponse deleteCompany(DeleteCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		return execute("DELETE", "api/CompanyCommand" + UrlParameterEncoder.encode(command), (client) -> {
			return handleResponse(client, DeleteCompanyResponse.class);
		});
	}

	public ArchiveCompanyResponse archiveCompany(ArchiveCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		return execute("POST", "api/CompanyCommand/Archive", (client) -> {
			writePayload(client, command);
			return handleResponse(client, ArchiveCompanyResponse.class);
		});
	}

	public UnarchiveCompanyResponse unarchiveCompany(UnarchiveCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		return execute("POST", "api/CompanyCommand/Unarchive", (client) -> {
			writePayload(client, command);
			return handleResponse(client, UnarchiveCompanyResponse.class);
		});
	}

	public CompanySearchQueryResult searchCompany(CompanySearchQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		return execute("GET", "api/CompanyQuery/Search/" + UrlParameterEncoder.encode(query), (client) -> {
			return handleResponse(client, CompanySearchQueryResult.class);
		});
	}

	public ViewCompanyQueryResult viewCompany(ViewCompanyQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		return execute("GET", "api/CompanyQuery/" + UrlParameterEncoder.encode(query), (client) -> {
			return handleResponse(client, ViewCompanyQueryResult.class);
		});
	}

	public ClassifyCompanyHitResponse classifyCompanyHit(ClassifyCompanyHitCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		return execute("POST", "api/CompanyCommand/Classify", (client) -> {
			writePayload(client, command);
			return handleResponse(client, ClassifyCompanyHitResponse.class);
		});
	}
}
