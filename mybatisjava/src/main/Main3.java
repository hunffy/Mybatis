package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.StudentMapper;
import mapper.StudentMapper3;

public class Main3 {
	private static SqlSessionFactory sqlMap;
	static {
		InputStream input = null;
	try {
		input = Resources.getResourceAsStream 
				("mapper/mybatis-config.xml");
	}catch(IOException e) {
		e.printStackTrace();
	}
	sqlMap = new SqlSessionFactoryBuilder().build(input); 
}
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		
		System.out.println("이몽룡 학생 추가하기");
		Student st = new Student();
		st.setStudno(1001);
		st.setName("이몽룡");
		st.setJumin("1234562345678");
		st.setId("leemy2");
		int result = //excuteupdate를 위해 excuteupdate >0 하기위해 int형사용
				session.getMapper(StudentMapper3.class).insert(st);
		System.out.println(result + "건 학생 정보에 추가");
		
		
		System.out.println("이몽룡 학생 조회하기");
		List<Student> list =
		session.getMapper(StudentMapper3.class).select_name("이몽룡");
		for(Student s : list) System.out.println(s);
//		session.commit(); //물리적인 저장공간에 DB가 추가됨. DB에있는 정보를 수정,삭제할때는 commit 필수
		
		
		//변경하기
		System.out.println("이몽룡 학생 학년:1, 몸무게:80, 키:175로 업데이트하기");
		st.setGrade(1);
		st.setWeight(80);
		st.setHeight(175);
		
		result = session.getMapper(StudentMapper3.class).update(st);
		System.out.println(result + "건 학생 정보에 변경");
		
		System.out.println("이몽룡 학생 조회하기");
		list =
				session.getMapper(StudentMapper3.class).select_name("이몽룡");
		for(Student s : list) System.out.println(s);
	
		//삭제하기
		System.out.println("1001번 학생의 정보 삭제하기");
		result = session.getMapper(StudentMapper3.class).delete(st.getStudno());
		System.out.println("이몽룡 학생 조회하기");
		list = 
				session.getMapper(StudentMapper3.class).select_name("이몽룡");
		for(Student s : list) System.out.println(s);
	}

}
