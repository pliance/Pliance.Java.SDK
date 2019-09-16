package pliance.sdk;

import com.google.gson.Gson;
import pliance.sdk.contracts.*;
import pliance.sdk.exceptions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * @author      Firstname Lastname address@example.com
 * @version     1.6                 (current version number of program)
 * @since       1.2          (the version of the package this class was first added to)
 */
public class PlianceClient implements IPlianceClient {
	private PlianceClientFactory _factory;
	private String _givenName;
	private String _subject;
	public String _source = null;
	private Gson _gson = new Gson();

	public PlianceClient(PlianceClientFactory factory, String givenName, String subject) {
		_subject = subject;
		_givenName = givenName;
		_factory = factory;
	}

	private <T> T execute(String method, String path, Func1<HttpURLConnection, T, Exception> action)
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

		_source = stringBuilder.toString();
		return _source;
	}

	public RegisterPersonResponse registerPerson(RegisterPersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		String json = _gson.toJson(command);

		return execute("PUT", "api/PersonCommand", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			return handleResponse(client, RegisterPersonResponse.class);
		});
	}

	private <T extends Response> T handleResponse(HttpURLConnection client, Class<T> type) throws Exception {
		if (client.getResponseCode() != 200) {
			throw new HttpException(
					"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
		}

		String response = convert(client.getInputStream());
		T result = _gson.fromJson(response, type);

		if (!result.success) {
			throw new HttpException("bad result");
		}

		return result;
	}

	public ArchivePersonResponse archivePerson(ArchivePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		String json = _gson.toJson(command);

		return execute("POST", "api/PersonCommand/Archive", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			return handleResponse(client, ArchivePersonResponse.class);
		});
	}

	public UnarchivePersonResponse unarchivePerson(UnarchivePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		String json = _gson.toJson(command);

		return execute("POST", "api/PersonCommand/Unarchive", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			return handleResponse(client, UnarchivePersonResponse.class);
		});
	}

	public DeletePersonResponse deletePerson(DeletePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		return execute("DELETE", "api/PersonCommand" + UrlParameterEncoder.encode(command), (client) -> {
			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			return handleResponse(client, DeletePersonResponse.class);
		});
	}

	public ClassifyHitResponse classifyPersonHit(ClassifyHitCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		String json = _gson.toJson(command);

		return execute("POST", "api/PersonCommand/Classify", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();

			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			return handleResponse(client, ClassifyHitResponse.class);
		});
	}

	public PersonSearchQueryResult searchPerson(PersonSearchQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		return execute("GET", "api/PersonQuery/Search/" + UrlParameterEncoder.encode(query), (client) -> {
			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

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

		String json = _gson.toJson(command);

		return execute("PUT", "api/CompanyCommand", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

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

		String json = _gson.toJson(command);

		return execute("POST", "api/CompanyCommand/Archive", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			return handleResponse(client, ArchiveCompanyResponse.class);
		});
	}

	public UnarchiveCompanyResponse unarchiveCompany(UnarchiveCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		String json = _gson.toJson(command);

		return execute("POST", "api/CompanyCommand/Unarchive", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

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

	public String Source() {
		return _source;
	}
}
