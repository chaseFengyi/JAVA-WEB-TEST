package junit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.test1.Person;

public class PersonTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testEat(){
		Person person = new Person();
		person.eat();
	}
	
	@Test
	public void testRun(){

		Person person = new Person();
		person.run();
	}
}
