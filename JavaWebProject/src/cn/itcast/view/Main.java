package cn.itcast.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.itcast.dao.StudentDao;

import com.itcast.domain.Student;

public class Main {

	public static void main(String[] args) throws IOException {

		System.out.println("���ѧ����a�� ����ѧ�� ��b�� ɾ��ѧ�� ��c��");
		System.out.print("�������������:");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String type = br.readLine();

		if (type.equalsIgnoreCase("a")) {
			try {
				System.out.println("������ѧ������:");
				String name = br.readLine();

				System.out.println("������ѧ��׼��֤��:");
				String examid = br.readLine();

				System.out.println("������ѧ�����֤��:");
				String idcard = br.readLine();

				System.out.println("������ѧ�����ڵ�:");
				String location = br.readLine();

				System.out.println("������ѧ���ɼ�:");
				String grade = br.readLine();

				Student student = new Student();
				student.setExamid(examid);
				student.setIdcard(idcard);
				student.setGrade(Double.parseDouble(grade));
				student.setLoaction(location);
				student.setName(name);

				StudentDao dao = new StudentDao();
				dao.add(student);

				System.out.println("�ɹ�");
			} catch (Exception e) {
				System.out.println("ʧ��");
			}

		} else if (type.equalsIgnoreCase("b")) {

			System.out.print("������ѧ����׼��֤��:");
			String examid = br.readLine();

			StudentDao sDao = new StudentDao();
			Student student = sDao.find(examid);
			if (student == null) {
				System.out.println("error");
			} else {
				System.out.println("��Ҫ���ҵ�ѧ����Ϣ���£�");
				System.out.println("������ " + student.getName());
				System.out.println("���֤�� " + student.getExamid());
				System.out.println("׼��֤�� " + student.getIdcard());
				System.out.println("���ڵأ� " + student.getLoaction());
				System.out.println("�ɼ� :" + student.getGrade());
			}

		} else if (type.equalsIgnoreCase("c")) {
			try {
				System.out.print("������Ҫɾ����ѧ����������");
				String name = br.readLine();

				StudentDao sDao = new StudentDao();
				sDao.delete(name);

				System.out.println("��ϲ�㣡ɾ���ɹ���");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} else {
			System.out.println("��֧�ִ������!!!");
		}
	}

}
