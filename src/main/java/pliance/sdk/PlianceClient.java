package pliance.sdk;

import com.google.gson.Gson;
import pliance.sdk.contracts.*;
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
	public String _source = null;

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

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return execute("PUT", "api/PersonCommand", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			RegisterPersonResponse result = gson.fromJson(response, RegisterPersonResponse.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public ArchivePersonResponse archivePerson(ArchivePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return execute("POST", "api/PersonCommand/Archive", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			ArchivePersonResponse result = gson.fromJson(response, ArchivePersonResponse.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public UnarchivePersonResponse unarchivePerson(UnarchivePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return execute("POST", "api/PersonCommand/Unarchive", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			UnarchivePersonResponse result = gson.fromJson(response, UnarchivePersonResponse.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public DeletePersonResponse deletePerson(DeletePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();

		return execute("DELETE", "api/PersonCommand" + UrlParameterEncoder.encode(command), (client) -> {
			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			DeletePersonResponse result = gson.fromJson(response, DeletePersonResponse.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public ClassifyHitResponse classifyPersonHit(ClassifyHitCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return execute("POST", "api/PersonCommand/Classify", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();

			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			ClassifyHitResponse result = gson.fromJson(response, ClassifyHitResponse.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public PersonSearchQueryResult searchPerson(PersonSearchQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		Gson gson = new Gson();

		return execute("GET", "api/PersonQuery/Search/" + UrlParameterEncoder.encode(query), (client) -> {
			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			PersonSearchQueryResult result = gson.fromJson(response, PersonSearchQueryResult.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public ViewPersonQueryResult viewPerson(ViewPersonQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		Gson gson = new Gson();

		return execute("GET", "api/PersonQuery/" + UrlParameterEncoder.encode(query), (client) -> {
			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			ViewPersonQueryResult result = gson.fromJson(response, ViewPersonQueryResult.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public PingResponse ping() throws PlianceApiException {
		Gson gson = new Gson();

		return execute("GET", "api/Ping", (client) -> {
			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			PingResponse result = gson.fromJson(response, PingResponse.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public RegisterCompanyResponse registerCompany(RegisterCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return execute("PUT", "api/CompanyCommand", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			RegisterCompanyResponse result = gson.fromJson(response, RegisterCompanyResponse.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public DeleteCompanyResponse deleteCompany(DeleteCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();

		return execute("DELETE", "api/CompanyCommand" + UrlParameterEncoder.encode(command), (client) -> {
			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			DeleteCompanyResponse result = gson.fromJson(response, DeleteCompanyResponse.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public ArchiveCompanyResponse archiveCompany(ArchiveCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return execute("POST", "api/CompanyCommand/Archive", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			ArchiveCompanyResponse result = gson.fromJson(response, ArchiveCompanyResponse.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public UnarchiveCompanyResponse unarchiveCompany(UnarchiveCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return execute("POST", "api/CompanyCommand/Unarchive", (client) -> {
			java.io.OutputStream stream = client.getOutputStream();
			stream.write(json.getBytes("UTF-8"));
			stream.flush();
			stream.close();

			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			UnarchiveCompanyResponse result = gson.fromJson(response, UnarchiveCompanyResponse.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public CompanySearchQueryResult searchCompany(CompanySearchQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		Gson gson = new Gson();

		return execute("GET", "api/CompanyQuery/Search/" + UrlParameterEncoder.encode(query), (client) -> {
			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			CompanySearchQueryResult result = gson.fromJson(response, CompanySearchQueryResult.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public ViewCompanyQueryResult viewCompany(ViewCompanyQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		Gson gson = new Gson();

		return execute("GET", "api/CompanyQuery/" + UrlParameterEncoder.encode(query), (client) -> {
			if (client.getResponseCode() != 200) {
				throw new HttpException(
						"Failed : HTTP error code : " + client.getResponseCode() + ", " + client.getResponseMessage());
			}

			String response = convert(client.getInputStream());
			ViewCompanyQueryResult result = gson.fromJson(response, ViewCompanyQueryResult.class);

			if (!result.success) {
				throw new HttpException("bad result");
			}

			return result;
		});
	}

	public String Source() {
		return _source;
	}
}
