package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import test1017.Professor;

public interface ProfessorMapper {
	
	@Select("select count(*) from professor") //모든 컬럼갯수조회
	int count();
	
	@Select("select * from professor") //교수테이블 모든 교수정보조회
	List<Professor> select1();
	
	
	@Select("select * from professor where deptno=#{deptno}") //교수중 101번학과의 교수정보출력
	List<Professor> select2(int i);
	
	@Select("select * from professor "
			+ "where name like'%${name}%' and position=#{position}") // 교수중 김씨성,전임강사만 출력하기
	List<Professor> select3(Map<String, Object> map);

	
	@Select("select * from professor "
	+ " where name like '%${name}%' and position=#{position}")
	List<Professor> select4(@Param("name")String name,
							@Param("position")String position);
	
	

}
