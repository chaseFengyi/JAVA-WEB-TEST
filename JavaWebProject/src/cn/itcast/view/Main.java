package cn.itcast.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.itcast.dao.StudentDao;

import com.itcast.domain.Student;

public class Main {

	public static void main(String[] args) throws IOException {

		System.out.println("添加学生（a） 查找学生 （b） 删除学生 （c）");
		System.out.print("请输入操作类型:");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String type = br.readLine();

		if (type.equalsIgnoreCase("a")) {
			try {
				System.out.println("请输入学生姓名:");
				String name = br.readLine();

				System.out.println("请输入学生准考证号:");
				String examid = br.readLine();

				System.out.println("请输入学生身份证号:");
				String idcard = br.readLine();

				System.out.println("请输入学生所在地:");
				String location = br.readLine();

				System.out.println("请输入学生成绩:");
				String grade = br.readLine();

				Student student = new Student();
				student.setExamid(examid);
				student.setIdcard(idcard);
				student.setGrade(Double.parseDouble(grade));
				student.setLoaction(location);
				student.setName(name);

				StudentDao dao = new StudentDao();
				dao.add(student);

				System.out.println("成功");
			} catch (Exception e) {
				System.out.println("失败");
			}

		} else if (type.equalsIgnoreCase("b")) {

			System.out.print("请输入学生的准考证号:");
			String examid = br.readLine();

			StudentDao sDao = new StudentDao();
			Student student = sDao.find(examid);
			if (student == null) {
				System.out.println("error");
			} else {
				System.out.println("您要查找的学生信息如下：");
				System.out.println("姓名： " + student.getName());
				System.out.println("身份证： " + student.getExamid());
				System.out.println("准考证： " + student.getIdcard());
				System.out.println("所在地： " + student.getLoaction());
				System.out.println("成绩 :" + student.getGrade());
			}

		} else if (type.equalsIgnoreCase("c")) {
			try {
				System.out.print("请输入要删除的学生的姓名：");
				String name = br.readLine();

				StudentDao sDao = new StudentDao();
				sDao.delete(name);

				System.out.println("恭喜你！删除成功！");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} else {
			System.out.println("不支持此类操作!!!");
		}
	}

}
