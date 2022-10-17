package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.StudentMapper;

public class Main1 {
	private static SqlSessionFactory sqlMap;
	
	//static 초기화 블럭 :
	//바이트형 입력 스트림   스트림:데이터통로
	static {
		InputStream input = null;
	try {
		input = Resources.getResourceAsStream //스트림을 통해 ("")파일을 읽어올수있도록 통로연결
				("mapper/mybatis-config.xml");
	}catch(IOException e) {
		e.printStackTrace();
	}
	//sqlMap : mybatis의 환경설정부분을 담아둔 객체
	sqlMap = new SqlSessionFactoryBuilder().build(input); 
}
	
	
	private static Class<StudentMapper> cls = StudentMapper.class;
	public static void main(String[] args) { //모든 프로그램의 시작
		
		//session: sqlMap 객체에 저장된 DB로 접속
		SqlSession session = sqlMap.openSession();
		System.out.println("모든 학생 정보 조회하기");
		List<Student> list = session.getMapper(cls).select();
		for(Student s : list) System.out.println(s);
		
		//1학년 학생만 조회해보기
		System.out.println("1학년 학생 정보 조회하기");
		list = session.getMapper(cls).select_grade(1);
		for(Student s : list) System.out.println(s);
		
		//1학년 학생만 조회해보기
		System.out.println("2학년 학생 정보 조회하기");
		list = session.getMapper(cls).select_grade(2);
		for(Student s : list) System.out.println(s);
		
		//9711학번 조회하기
		System.out.println("학번으로 학생 정보 조회하기");
		list = session.getMapper(cls).select_studno("9711");
		for(Student s : list) System.out.println(s);
		
		System.out.println("이름에 김자가 들어가는 학생 정보 조회하기");
		list = session.getMapper(cls).select_like("김");
		for(Student s : list) System.out.println(s);
		
		System.out.println("이름에 김자가 들어가는 학생 정보 조회하기2");
		list = session.getMapper(cls).select_like2("%김%");
		for(Student s : list) System.out.println(s);
	}

}
