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
		@Select({"<script>",
			"select * from student ",
			"<trim prefix='where' prefixOverrides='AND||OR'>", //and나 or를 where로 바꿔줘
			"<if test='grade != null'>and grade = #{grade}</if>",//grade가 null이 아니면 <if>값 sql구문에 추가</if>
			"<if test='height != null'>and height = #{height}</if>",//height가 null 아니면 <if>값 sql구문에 추가</if>
			"</trim>",
			"</script>"
		})
		List<Student> select2(Map<String, Object> map);
		/* 
		 * 1. map : grade:1, height:175
		 * 
		 * select * from student
		 * where grade = #{grade}
		 * and height >= #{height}
		 * 
		 * 2. map : height:175
		 * 
		 * select * from student and height >= #{height}
		 * and 나 or를 where로 바꿔지니까,
		 * select * from student where height >= #{height}
		 * 
		 * 3. map : grade:2
		 * select * from student where grade = #{garde}
		 * 
		 * 4. map :null
		 * 
		 * select * from student
		 * 
		 */
		
		//grade,height 중  한개만 입력될때 사용되는 동적 sql구문
		@Select({"<script>",
			"select * from student ",
			"<if test='grade != null'>where grade = #{grade}</if>",
			"<if test='height != null'>where height = #{height}</if>",
			"</script>"
			})
			List<Student> select3(Map<String, Object> map);
		
		
		@Select({"<script>",
			"select * from student",
			"<if test='datas != null'>where ${columns} in " //$로 해야함 #은 ''형태로 가져오기때문
			+ "<foreach collection='datas' item='d' separator=','" //datas 값을 d로 인식 사이사이를 ,로 인식
			+ " open='(' close=')'>#{d}</foreach></if>",
			"</script>"})
		List<Student> select4(Map<String, Object> map);

		/* datas = [101,201,301]
		 * columns = "deptno1"
		 * 
		 * select * from student where deptno1 in (101,201,301)
		 * 
		 * 
		 * datas = ["이서진","구유미","일지매"]
		 * columns = "name"
		 * 
		 * select * from student where name in ("이서진","구유미","일지매")
		 * 
		 */
		
		
	}

