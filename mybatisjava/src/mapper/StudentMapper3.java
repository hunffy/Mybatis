package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import main.Student;

public interface StudentMapper3 {
	
	
	@Insert("insert into student (studno,name,jumin,id)"
			+ " values (#{studno},#{name},#{jumin},#{id})")
	int insert(Student st); //excuteupdate를 위해 excuteupdate >0 하기위해 int형사용 

	@Select("select * from student where name=#{name}")
	List<Student> select_name(String string);
	
	@Update("update student set grade=#{grade}, weight=#{weight},"
			+ "height=#{height} where studno=#{studno}")
	int update(Student st);

	
	@Delete("delete from student where studno=#{studno}")
	int delete(int studno);


	
}
