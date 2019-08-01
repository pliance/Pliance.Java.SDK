public class Arg {
	public String Hello = "Hello World";
	public Integer A = 12;
	public Integer B = null;
	public Inner C = new Inner();
	public int D = 13;

	protected class Inner {
		public Integer A = 12;
		public int[] List = new int[2];
	}
}