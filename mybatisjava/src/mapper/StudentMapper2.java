package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import main.Student;
/*	같은 이름을 가진 메서드가 여러개 존재불가 : Mybatis에서 오류 발생
 * 	마이바티스 인터페이스는 오버로딩 불가함.
 * 	메서드 이름 : sql 구문을 구분할 수 있는 이름.
 */
public interface StudentMapper2 {
	
	
	//동적 sql 문장
	@Select({"<script>",
			"select * from student",
			"<choose>"
			+ "<when test='grade != null and height != null'>"
			+ "where grade = #{grade} and height >= #{height}</when>"
			+ "<when test='grade != null'>where grade=#{grade}</when>"
			+ "<when test='height != null'>where height >= #{height}</when>"
			+ "</choose>",
			"</script>"})
			List<Student> select(Map<String, Object> map);
	
	/*	<when test='grade != null and height != null'>
	 * 	: map 객체에 grade(key값), height(key값) 값이 둘다 존재하는경우 
	 * 	  {"grade":1, "height":175}
	 * 	
	 *	 => select * from student
	 *	 where grade= 1 and height  >=175 구문이 실행 후 출력됨.
	 *
	 *	2.
	 *	map : {"height":175}
	 *	<when test='height != null'>where height >= #{height}</when>
	 *	height값이 null이 아닌 175가 존재하니,
	 *	select * from student
	 *	where height >=175 구문 실행 후 출력
	 *
	 *	3.
	 *	map : {"grade":2}
	 *	<when test='grade != null'>where grade=#{grade}></when>
	 *	grade값이 null이 아닌 2가 존재하니,
	 *	select * from student
	 *	where grade =2 구문 실행 후 출력
	 *
	 *	4.
	 *	map :null
	 *	select * from student => 모든 데이터 조회.
	 * 
	 */
	}

