public class Program {
	public static void main(String[] args) {
		Arg arg = new Arg();

		try {
			System.out.println(UrlParameterEncoder.Encode(arg));
		} catch (Exception e) {
			System.out.println("Exception");
		}
	}
}
