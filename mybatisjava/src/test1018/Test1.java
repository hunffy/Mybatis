package test1018;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.ProfessorMapper2;

public class Test1 {
	private static SqlSessionFactory sqlMap;
	private static Map<String, Object> map = new HashMap<>();
	static {
		InputStream input = null;
		try {
			input = Resources.getResourceAsStream
					("mapper/mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);
	}

	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("1.모든 교수 정보 조회하기");
		List<Professor> list = 
			session.getMapper(ProfessorMapper2.class).select(null);
		for (Professor s : list)System.out.println(s);
		
		System.out.println("2.101 학과 교수 정보 조회하기");
		//select * from professor where deptno=101
		map.clear();
		map.put("deptno", 101);
		list = session.getMapper(ProfessorMapper2.class).select(map);
		for (Professor s : list)System.out.println(s);

		System.out.println("3.교수 번호가 1001번인 교수 정보 조회하기");
		map.clear();
		map.put("profno", 1001);
		list = session.getMapper(ProfessorMapper2.class).select(map);
		for (Professor s : list) System.out.println(s);

		System.out.println("4.정교수 정보 조회하기");
		//select * from professor where position='정교수'
		map.clear();
		map.put("position", "정교수");
		list = session.getMapper(ProfessorMapper2.class).select(map);
		for (Professor s : list) System.out.println(s);

		System.out.println("5.101 학과의 정교수 정보 조회하기");
		//select * from professor where deptno=101 and position='정교수'
		map.clear();
		map.put("deptno", 101);
		map.put("position", "정교수");
		list = session.getMapper(ProfessorMapper2.class).select(map);
		for (Professor s : list) System.out.println(s);
		
		System.out.println("6.101,201 학과의 교수 정보 조회하기 ");
		map.clear();
		map.put("datas", Arrays.asList(101,201));
		list = session.getMapper(ProfessorMapper2.class).select(map);
		for (Professor s : list) System.out.println(s);

		
		System.out.println("7.101,201 학과  조교수 정보 조회하기");
		//select * from professor where position=#{position} and deptno in (101,201)
		map.clear();
		map.put("datas", Arrays.asList(101,201));
		map.put("position", "조교수");
		list = session.getMapper(ProfessorMapper2.class).select(map);
		for (Professor s : list) System.out.println(s);		
	}
}