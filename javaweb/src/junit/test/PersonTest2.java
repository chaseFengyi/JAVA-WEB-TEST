package junit.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonTest2 {

	@Before
	public void setUp() throws Exception {
		System.out.println("测试方法运行之前运行!!");
	}

	@After
	public void tearDown() throws Exception{
		System.out.println("测试方法运行之后运行!!!");
	}

	@Test
	public void test() {
		System.out.println("text");
	}

}
