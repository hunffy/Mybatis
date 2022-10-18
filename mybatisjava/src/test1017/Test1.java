package test1017;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.ProfessorMapper;

/*
1. 교수테이블에 등록된 레코드의 건수를 출력하기.
2. 교수테이블에 등록된 모든 정보를 출력하기
3. 교수중 101번 학과의 교수 정보를 출력하기
4. 교수중 성이 김씨인 시간강사 정보를 출력하기 
*/
public class Test1 {
	private static SqlSessionFactory sqlMap;
	private static Map<String, Object> map = new HashMap<>();
	static {
		InputStream input = null;
		try {
			input = Resources.getResourceAsStream("mapper/mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);
	}
	
	public static void main(String[] args) {
		System.out.println("1. 교수테이블에 등록된 레코드의 건수를 출력하기.");
		int x = 0;
		SqlSession session = sqlMap.openSession();
		x = session.getMapper(ProfessorMapper.class).count();
		System.out.println("professor 테이블의 레코드 갯수:"+x);		

		System.out.println("2. 교수테이블에 등록된 모든 정보를 출력하기");
		List<Professor> list= session.getMapper(ProfessorMapper.class).select1();
		for(Professor p : list) System.out.println(p);

		System.out.println("3. 교수중 101번 학과의 교수 정보를 출력하기");
		list= session.getMapper(ProfessorMapper.class).select2(101);
		for(Professor p : list) System.out.println(p);
		
		System.out.println("4. 교수중 성이 김씨인 전임강사 정보를 출력하기");
		map.clear();
		map.put("name","김");
		map.put("position", "전임강사");
		list= session.getMapper(ProfessorMapper.class).select3(map);
		for(Professor p : list) System.out.println(p);

		System.out.println("5. 교수중 성이 김씨인 전임강사 정보를 출력하기");
		list= session.getMapper(ProfessorMapper.class).select4("김","전임강사");
		for(Professor p : list) System.out.println(p);
	}
}