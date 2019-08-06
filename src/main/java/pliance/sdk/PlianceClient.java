package pliance.sdk;

import com.google.gson.Gson;
import pliance.sdk.Contracts.*;
import pliance.sdk.Exceptions.*;
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

	private <T> T Execute(String path, Function<HttpsURLConnection, T> action) throws Exception {
		return _factory.Execute(action, path, _givenName, _subject);
	}

	public RegisterPersonResponse RegisterPerson(RegisterPersonCommand command) throws Exception {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/PersonCommand", (client) -> {
			try {
				client.setRequestMethod("PUT");
				java.io.OutputStream stream = client.getOutputStream();

				System.out.println("Json: " + json);
				stream.write(json.getBytes("UTF-8"));
				stream.flush();
				stream.close();

				if (client.getResponseCode() != 200) {
					throw new PlianceApiException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());

				System.out.println("Response: " + response);
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

	@Override
	public ArchivePersonResponse ArchivePerson(ArchivePersonCommand command) throws Exception {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/PersonCommand/Archive", (client) -> {
			try {
				client.setRequestMethod("POST");
				java.io.OutputStream stream = client.getOutputStream();

				System.out.println("Json: " + json);
				stream.write(json.getBytes("UTF-8"));
				stream.flush();
				stream.close();

				if (client.getResponseCode() != 200) {
					throw new PlianceApiException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());

				System.out.println("Response: " + response);
				return gson.fromJson(response, ArchivePersonResponse.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public DeletePersonResponse DeletePerson(DeletePersonCommand command) throws Exception {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/PersonCommand", (client) -> {
			try {
				client.setRequestMethod("DELETE");
				java.io.OutputStream stream = client.getOutputStream();

				System.out.println("Json: " + json);
				stream.write(json.getBytes("UTF-8"));
				stream.flush();
				stream.close();

				if (client.getResponseCode() != 200) {
					throw new PlianceApiException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());

				System.out.println("Response: " + response);
				return gson.fromJson(response, DeletePersonResponse.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public ClassifyHitResponse ClassifyPersonHit(ClassifyHitCommand command) throws Exception {
		if (command == null) {
			throw new ArgumentNullException("Command");
		}

		Gson gson = new Gson();
		String json = gson.toJson(command);

		return Execute("api/PersonCommand/Classify", (client) -> {
			try {
				client.setRequestMethod("POST");
				java.io.OutputStream stream = client.getOutputStream();

				System.out.println("Json: " + json);
				stream.write(json.getBytes("UTF-8"));
				stream.flush();
				stream.close();

				if (client.getResponseCode() != 200) {
					throw new PlianceApiException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());

				System.out.println("Response: " + response);
				return gson.fromJson(response, ClassifyHitResponse.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public PersonSearchQueryResult SearchPerson(PersonSearchQuery query) throws Exception {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		Gson gson = new Gson();

		return Execute("api/PersonQuery/Search/" + UrlParameterEncoder.Encode(query), (client) -> {
			try {
				client.setRequestMethod("GET");
				if (client.getResponseCode() != 200) {
					throw new PlianceApiException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				System.out.println("Response: " + response);
				return gson.fromJson(response, PersonSearchQueryResult.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public ViewPersonQueryResult ViewPerson(ViewPersonQuery query) throws Exception {
		if (query == null) {
			throw new ArgumentNullException("query");
		}

		Gson gson = new Gson();

		return Execute("api/PersonQuery/" + UrlParameterEncoder.Encode(query), (client) -> {
			try {
				client.setRequestMethod("GET");
				if (client.getResponseCode() != 200) {
					throw new PlianceApiException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				System.out.println("Response: " + response);
				return gson.fromJson(response, ViewPersonQueryResult.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public PingResponse Ping() throws Exception {
		Gson gson = new Gson();

		return Execute("api/Ping", (client) -> {
			try {
				client.setRequestMethod("GET");
				if (client.getResponseCode() != 200) {
					throw new PlianceApiException("Failed : HTTP error code : " + client.getResponseCode() + ", "
							+ client.getResponseMessage());
				}

				String response = Convert(client.getInputStream());
				System.out.println("Response: " + response);
				return gson.fromJson(response, PingResponse.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public RegisterCompanyResponse RegisterCompany(RegisterCompanyCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteCompanyResponse DeleteCompany(DeleteCompanyCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArchiveCompanyResponse ArchiveCompany(ArchiveCompanyCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanySearchQueryResult SearchCompany(CompanySearchQuery request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViewCompanyQueryResult ViewCompany(ViewCompanyQuery request) {
		// TODO Auto-generated method stub
		return null;
	}
}
/*
 * public ArchivePersonResponse ArchivePerson(ArchivePersonCommand command) { if
 * (command == null) { throw new ArgumentNullException(nameof(command)); }
 * 
 * Gson gson = new Gson(); String json = gson.toJson(command);
 * 
 * return Execute((client) = { var response =
 * client.PostAsync("api/PersonCommand/Archive", content); var responseString =
 * response.Content.ReadAsStringAsync(); var result =
 * JsonConvert.DeserializeObject<ArchivePersonResponse(responseString);
 * 
 * if (!result.Success) { throw new Exception(result.Message); }
 * 
 * return result; }); }
 * 
 * public DeletePersonResponse DeletePerson(DeletePersonCommand command) { if
 * (command == null) { throw new ArgumentNullException(nameof(command)); }
 * 
 * Gson gson = new Gson(); String json = gson.toJson(command);
 * 
 * return Execute((client) = { var response =
 * client.DeleteAsync("api/PersonCommand" + command.UrlEncoded()); var
 * responseString = response.Content.ReadAsStringAsync(); var result =
 * JsonConvert.DeserializeObject<DeletePersonResponse(responseString);
 * 
 * if (!result.Success) { throw new Exception(result.Message); }
 * 
 * return result; }); }
 * 
 * public ClassifyHitResponse ClassifyPersonHit(ClassifyHitCommand command) { if
 * (command == null) { throw new ArgumentNullException(nameof(command)); }
 * 
 * Gson gson = new Gson(); String json = gson.toJson(command);
 * 
 * return Execute((client) = { var response =
 * client.PostAsync("api/PersonCommand/Classify", content); var responseString =
 * response.Content.ReadAsStringAsync(); var result =
 * JsonConvert.DeserializeObject<ClassifyHitResponse(responseString);
 * 
 * if (!result.Success) { throw new Exception(result.Message); }
 * 
 * return result; }); }
 * 
 * public Task Ping() { Execute<object((client) = { var response =
 * client.GetAsync("api/Ping");
 * 
 * var responseString = response.Content.ReadAsStringAsync(); return null; }); }
 * 
 * public PersonSearchQueryResult SearchPerson(PersonSearchQuery query) { if
 * (query == null) { throw new ArgumentNullException(nameof(query)); }
 * 
 * return Execute((client) = { var response =
 * client.GetAsync("api/PersonQuery/Search/" + query.UrlEncoded()); var
 * responseString = response.Content.ReadAsStringAsync(); var result =
 * JsonConvert.DeserializeObject<PersonSearchQueryResult(responseString);
 * 
 * if (!result.Success) { throw new Exception(result.Message); }
 * 
 * return result; }); }
 * 
 * public ViewPersonQueryResult ViewPerson(ViewPersonQuery query) { if (query ==
 * null) { throw new ArgumentNullException(nameof(query)); }
 * 
 * return Execute((client) = { var response =
 * client.GetAsync($"api/PersonQuery/" + query.UrlEncoded()); var responseString
 * = response.Content.ReadAsStringAsync(); var result =
 * JsonConvert.DeserializeObject<ViewPersonQueryResult(responseString);
 * 
 * if (!result.Success) { throw new Exception(result.Message); }
 * 
 * return result; }); }
 * 
 * public RegisterCompanyResponse RegisterCompany(RegisterCompanyCommand
 * command) { if (command == null) { throw new
 * ArgumentNullException(nameof(command)); }
 * 
 * Gson gson = new Gson(); String json = gson.toJson(command);
 * 
 * return Execute((client) = { var response =
 * client.PostAsync("api/PersonCompany", content); var responseString =
 * response.Content.ReadAsStringAsync(); var result =
 * JsonConvert.DeserializeObject<RegisterCompanyResponse(responseString);
 * 
 * if (!result.Success) { throw new Exception(result.Message); }
 * 
 * return result; }); }
 * 
 * public DeleteCompanyResponse DeleteCompany(DeleteCompanyCommand command) { if
 * (command == null) { throw new ArgumentNullException(nameof(command)); }
 * 
 * Gson gson = new Gson(); String json = gson.toJson(command);
 * 
 * return Execute((client) = { var response =
 * client.DeleteAsync("api/PersonCompany/Archive" + command.UrlEncoded()); var
 * responseString = response.Content.ReadAsStringAsync(); var result =
 * JsonConvert.DeserializeObject<DeleteCompanyResponse(responseString);
 * 
 * if (!result.Success) { throw new Exception(result.Message); }
 * 
 * return result; }); }
 * 
 * public ArchiveCompanyResponse ArchiveCompany(ArchiveCompanyCommand command) {
 * if (command == null) { throw new ArgumentNullException(nameof(command)); }
 * 
 * Gson gson = new Gson(); String json = gson.toJson(command);
 * 
 * return Execute((client) = { var response =
 * client.PostAsync("api/PersonCompany/Archive", content); var responseString =
 * response.Content.ReadAsStringAsync(); var result =
 * JsonConvert.DeserializeObject<ArchiveCompanyResponse(responseString);
 * 
 * if (!result.Success) { throw new Exception(result.Message); }
 * 
 * return result; }); }
 * 
 * public CompanySearchQueryResult SearchCompany(CompanySearchQuery query) { if
 * (query == null) { throw new ArgumentNullException(nameof(query)); }
 * 
 * return Execute((client) = { var response =
 * client.GetAsync("api/CompanyQuery/Search/" + query.UrlEncoded()); var
 * responseString = response.Content.ReadAsStringAsync(); var result =
 * JsonConvert.DeserializeObject<CompanySearchQueryResult(responseString);
 * 
 * if (!result.Success) { throw new Exception(result.Message); }
 * 
 * return result; }); }
 * 
 * public ViewCompanyQueryResult ViewCompany(ViewCompanyQuery query) { if (query
 * == null) { throw new ArgumentNullException(nameof(query)); }
 * 
 * return Execute((client) = { var response =
 * client.GetAsync($"api/CompanyQuery/" + query.UrlEncoded()); var
 * responseString = response.Content.ReadAsStringAsync(); var result =
 * JsonConvert.DeserializeObject<ViewCompanyQueryResult(responseString);
 * 
 * if (!result.Success) { throw new Exception(result.Message); }
 * 
 * return result; }); } }}
 */