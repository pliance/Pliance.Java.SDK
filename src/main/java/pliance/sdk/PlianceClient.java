package pliance.sdk;

import com.google.gson.Gson;
import pliance.sdk.contracts.*;
import pliance.sdk.exceptions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.function.Function;

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

	private <T> T Execute(String path, Function<HttpURLConnection, T> action) throws PlianceApiException {
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
				RegisterPersonResponse result = gson.fromJson(response, RegisterPersonResponse.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;
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

		_source = stringBuilder.toString();
		return _source;
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
				ArchivePersonResponse result = gson.fromJson(response, ArchivePersonResponse.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public UnarchivePersonResponse UnarchivePerson(UnarchivePersonCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/PersonCommand/Unarchive", (client) -> {
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
				UnarchivePersonResponse result = gson.fromJson(response, UnarchivePersonResponse.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
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

		return Execute("api/PersonCommand" + UrlParameterEncoder.Encode(command), (client) -> {
			try {
				client.setRequestMethod("DELETE");

				if (client.getResponseCode() != 200) {
					throw new HttpException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				DeletePersonResponse result = gson.fromJson(response, DeletePersonResponse.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
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
				ClassifyHitResponse result = gson.fromJson(response, ClassifyHitResponse.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
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
				PersonSearchQueryResult result = gson.fromJson(response, PersonSearchQueryResult.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
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
				ViewPersonQueryResult result = gson.fromJson(response, ViewPersonQueryResult.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
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
				PingResponse result = gson.fromJson(response, PingResponse.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public RegisterCompanyResponse RegisterCompany(RegisterCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/CompanyCommand", (client) -> {
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
				RegisterCompanyResponse result = gson.fromJson(response, RegisterCompanyResponse.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public DeleteCompanyResponse DeleteCompany(DeleteCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();

		return Execute("api/CompanyCommand" + UrlParameterEncoder.Encode(command), (client) -> {
			try {
				client.setRequestMethod("DELETE");

				if (client.getResponseCode() != 200) {
					throw new HttpException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				DeleteCompanyResponse result = gson.fromJson(response, DeleteCompanyResponse.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public ArchiveCompanyResponse ArchiveCompany(ArchiveCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/CompanyCommand/Archive", (client) -> {
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
				ArchiveCompanyResponse result = gson.fromJson(response, ArchiveCompanyResponse.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public UnarchiveCompanyResponse UnarchiveCompany(UnarchiveCompanyCommand command) throws PlianceApiException {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/CompanyCommand/Unarchive", (client) -> {
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
				UnarchiveCompanyResponse result = gson.fromJson(response, UnarchiveCompanyResponse.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public CompanySearchQueryResult SearchCompany(CompanySearchQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		Gson gson = new Gson();

		return Execute("api/CompanyQuery/Search/" + UrlParameterEncoder.Encode(query), (client) -> {
			try {
				client.setRequestMethod("GET");
				if (client.getResponseCode() != 200) {
					throw new HttpException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				CompanySearchQueryResult result = gson.fromJson(response, CompanySearchQueryResult.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public ViewCompanyQueryResult ViewCompany(ViewCompanyQuery query) throws PlianceApiException {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		Gson gson = new Gson();

		return Execute("api/CompanyQuery/" + UrlParameterEncoder.Encode(query), (client) -> {
			try {
				client.setRequestMethod("GET");
				if (client.getResponseCode() != 200) {
					throw new HttpException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				ViewCompanyQueryResult result = gson.fromJson(response, ViewCompanyQueryResult.class);

				if (!result.success) {
					throw new HttpException("bad result");
				}

				return result;				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public String Source() {
		return _source;
	}
}
