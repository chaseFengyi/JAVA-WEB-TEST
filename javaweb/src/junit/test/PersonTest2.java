package junit.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonTest2 {

	@Before
	public void setUp() throws Exception {
		System.out.println("���Է�������֮ǰ����!!");
	}

	@After
	public void tearDown() throws Exception{
		System.out.println("���Է�������֮������!!!");
	}

	@Test
	public void test() {
		System.out.println("text");
	}

}
