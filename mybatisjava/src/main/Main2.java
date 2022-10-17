package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.StudentMapper2;

public class Main2 {
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
private static Class<StudentMapper2> cls = StudentMapper2.class;
private static Map<String,Object> map = new HashMap<>();
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		
		
		System.out.println("1학년 학생 중 키가 175 이상 학생 정보");
		map.clear();
		map.put("grade", 1);
		map.put("height", 175);
		List<Student> list = session.getMapper(cls).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("키가 175이상 학생인 정보만 조회하기");
		map.clear();
		map.put("height", 175);
		list = session.getMapper(cls).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("2학년 학생 정보");
		map.clear();
		map.put("grade", 2);
		list = session.getMapper(cls).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("모든 학생 정보");
		list = session.getMapper(cls).select(null);
		for(Student s : list) System.out.println(s);
	}

}
