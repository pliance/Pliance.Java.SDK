package pliance.sdk;

import com.google.gson.Gson;
import pliance.sdk.contracts.*;
import pliance.sdk.exceptions.*;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Function;

public class PlianceClient implements IPlianceClient {
	private PlianceClientFactory _factory;
	private String _givenName;
	private String _subject;

	public PlianceClient(PlianceClientFactory factory, String givenName, String subject) {
		_subject = subject;
		_givenName = givenName;
		_factory = factory;
	}

	private <T> T Execute(String path, Function<HttpsURLConnection, T> action) throws PlianceApiException {
		return _factory.Execute(action, path, _givenName, _subject);
	}

	public RegisterPersonResponse RegisterPerson(RegisterPersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/PersonCommand", (client) -> {
			try {
				client.setRequestMethod("PUT");
				java.io.OutputStream stream = client.getOutputStream();
				stream.write(json.getBytes("UTF-8"));
				stream.flush();
				stream.close();

				if (client.getResponseCode() != 200) {
					throw new HttpException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				return gson.fromJson(response, RegisterPersonResponse.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	private String Convert(InputStream inputStream) throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
		String line = null;

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		}

		return stringBuilder.toString();
	}

	public ArchivePersonResponse ArchivePerson(ArchivePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/PersonCommand/Archive", (client) -> {
			try {
				client.setRequestMethod("POST");
				java.io.OutputStream stream = client.getOutputStream();
				stream.write(json.getBytes("UTF-8"));
				stream.flush();
				stream.close();

				if (client.getResponseCode() != 200) {
					throw new HttpException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				return gson.fromJson(response, ArchivePersonResponse.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public DeletePersonResponse DeletePerson(DeletePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/PersonCommand", (client) -> {
			try {
				client.setRequestMethod("DELETE");
				java.io.OutputStream stream = client.getOutputStream();

				stream.write(json.getBytes("UTF-8"));
				stream.flush();
				stream.close();

				if (client.getResponseCode() != 200) {
					throw new HttpException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				return gson.fromJson(response, DeletePersonResponse.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public ClassifyHitResponse ClassifyPersonHit(ClassifyHitCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/PersonCommand/Classify", (client) -> {
			try {
				client.setRequestMethod("POST");
				java.io.OutputStream stream = client.getOutputStream();

				stream.write(json.getBytes("UTF-8"));
				stream.flush();
				stream.close();

				if (client.getResponseCode() != 200) {
					throw new HttpException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				return gson.fromJson(response, ClassifyHitResponse.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public PersonSearchQueryResult SearchPerson(PersonSearchQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		Gson gson = new Gson();

		return Execute("api/PersonQuery/Search/" + UrlParameterEncoder.Encode(query), (client) -> {
			try {
				client.setRequestMethod("GET");
				if (client.getResponseCode() != 200) {
					throw new HttpException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				return gson.fromJson(response, PersonSearchQueryResult.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public ViewPersonQueryResult ViewPerson(ViewPersonQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		Gson gson = new Gson();

		return Execute("api/PersonQuery/" + UrlParameterEncoder.Encode(query), (client) -> {
			try {
				client.setRequestMethod("GET");
				if (client.getResponseCode() != 200) {
					throw new HttpException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				return gson.fromJson(response, ViewPersonQueryResult.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public PingResponse Ping() throws PlianceApiException {
		Gson gson = new Gson();

		return Execute("api/Ping", (client) -> {
			try {
				client.setRequestMethod("GET");
				if (client.getResponseCode() != 200) {
					throw new HttpException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				return gson.fromJson(response, PingResponse.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public RegisterCompanyResponse RegisterCompany(RegisterCompanyCommand command) throws PlianceApiException {
		return null;
	}

	public DeleteCompanyResponse DeleteCompany(DeleteCompanyCommand command) throws PlianceApiException {
		return null;
	}

	public ArchiveCompanyResponse ArchiveCompany(ArchiveCompanyCommand command) throws PlianceApiException {
		return null;
	}

	public CompanySearchQueryResult SearchCompany(CompanySearchQuery request) throws PlianceApiException {
		return null;
	}

	public ViewCompanyQueryResult ViewCompany(ViewCompanyQuery request) throws PlianceApiException {
		return null;
	}
}
