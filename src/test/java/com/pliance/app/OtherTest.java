package com.pliance.app;

import junit.framework.Test;
import junit.framework.TestSuite;

public class OtherTest extends TestBase {
	public static Test suite() {
		return new TestSuite(OtherTest.class);
	}

	public OtherTest() throws Exception {
	}

	public void test_Ping() throws Exception {
		_client.ping();
	}
}
