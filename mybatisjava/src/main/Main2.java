package main;

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
//		List<Student> list = session.getMapper(cls).select(map);
		List<Student> list = session.getMapper(cls).select2(map);
		
		//오류 발생
//		List<Student> list = session.getMapper(cls).select3(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("키가 175이상 학생인 정보만 조회하기");
		map.clear();
		map.put("height", 175);
//		list = session.getMapper(cls).select(map);
//		list = session.getMapper(cls).select2(map);
		list = session.getMapper(cls).select3(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("2학년 학생 정보");
		map.clear();
		map.put("grade", 2);
//		list = session.getMapper(cls).select(map);
//		list = session.getMapper(cls).select2(map);
		list = session.getMapper(cls).select3(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("모든 학생 정보");
//		list = session.getMapper(cls).select(null);
//		list = session.getMapper(cls).select2(null);
		list = session.getMapper(cls).select3(null);
		for(Student s : list) System.out.println(s);
		
		//============================================
		System.out.println("101,201,301 학과에 속한 학생의 정보");
		//select * from student where deptno1 in (101,201,301)
		map.clear();
		List<Integer> depts = Arrays.asList(101,201,301); //depts 리스트객체에 101,201,301 값을 담은 배열 넣기
		map.put("datas", depts);	//datas key에 (101,201,301)의 값을 가진 배열넣기
		map.put("columns", "deptno1");	//columns key에 deptno1 값
		list = session.getMapper(cls).select4(map); //list 는 mapper 클래스의 select4를 요청하는데 select4쿼리문에 map객체를넣는다
		for(Student s : list) System.out.println(s); //mapper 클래스를통해 가져온값을 출력.
		
		
		System.out.println("학과 2가 101,201,301 학과에 속한 학생의 정보");
		map.clear();
		depts = Arrays.asList(101,201,301); 
		map.put("datas", depts);	
		map.put("columns", "deptno2");	
		list = session.getMapper(cls).select4(map); 
		for(Student s : list) System.out.println(s); 
		
		System.out.println("학생의 이름이 이서진,구유미,일지매인 학생의 정보");
		map.clear();
		map.put("datas", Arrays.asList("이서진","구유미","일지매"));
		map.put("columns", "name");
		list = session.getMapper(cls).select4(map);
		for(Student s : list) System.out.println(s);
	}

}
