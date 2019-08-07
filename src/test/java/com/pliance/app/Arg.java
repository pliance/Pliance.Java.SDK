package com.pliance.app;
import java.util.ArrayList;
import java.util.List;

public class Arg {
	public String Hello = "Hello World";
	public Integer A = 12;
	public Integer B = null;
	public Inner C = new Inner();
	public int D = 13;

	protected class Inner {
		public Integer A = 12;
		public int[] List1 = new int[2];
		public List<Integer> List2 = new ArrayList<Integer>();
		
		public Inner()
		{
			List2.add(12);
			List2.add(13);
		}
	}
}
