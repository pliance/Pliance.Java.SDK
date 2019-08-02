import Contracts.RegisterPersonCommand;

public class Program {
	public static void main(String[] args) {
//		Arg arg = new Arg();
//
//		try {
//			System.out.println(UrlParameterEncoder.Encode(arg));
//		} catch (Exception e) {
//			System.out.println("Exception");
//		}

		PlianceClientFactory factory = new PlianceClientFactory("secret", "Demo", "https://test.pliance.io", null);
		IPlianceClient client = factory.Create("givenname", "subject");
		RegisterPersonCommand command = new RegisterPersonCommand();

		command.PersonReferenceId = "ref/1";

		try {
			client.RegisterPerson(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
