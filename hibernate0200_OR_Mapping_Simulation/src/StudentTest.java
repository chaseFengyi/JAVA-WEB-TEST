import java.sql.SQLException;

import com.hibernate.model.Student;


public class StudentTest {
	public static void main(String[] args) throws Exception {
		Student s = new Student();
		s.setId(3);
		s.setName("lisi");
		s.setAge(12);
		
		Session session = new Session();
		
		session.save(s);
	}
}
