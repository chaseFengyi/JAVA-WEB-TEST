package junit.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.test1.Person;

public class PersonTest3 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("after");
	}

	@Test
	public void test() {
		Person person = new Person();
		person.eat();
	}

}
