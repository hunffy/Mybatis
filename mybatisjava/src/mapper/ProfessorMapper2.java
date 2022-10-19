package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import test1018.Professor;

public interface ProfessorMapper2 {
	
	@Select({"<script>",
		"select * from professor",
		"<if test='deptno != null'>where deptno = #{deptno}</if>",
		"<if test='profno != null'>where profno = #{profno}</if>",
		"<if test='position != null'>where position =#{position}</if>",
		"<choose>"
		+ "<when test='deptno != null and position != null'>"
		+ "where deptno = #{deptno} and position = #{position}</when>" 
		+ "</choose>",
		"</script>"
	})
	List<Professor> select(Map<String, Object> map);

}
