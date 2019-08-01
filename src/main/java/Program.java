public class Program {
	public static void main(String[] args) {
		Arg arg = new Arg();

		try {
			System.out.println(UrlParamaterEncoder.Encode(arg));
		} catch (Exception e) {
			System.out.println("Exception");
		}
	}
}
