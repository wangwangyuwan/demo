package git.wangwangyuwan.demo.mapper;

import git.wangwangyuwan.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,creator,tag,gmt_modified,gmt_create) values (#{title},#{description},#{creator},#{tag},#{gmtModified},#{gmtCreate})")
    void addQuestion(Question question);

    @Select("select * from question")
    List<Question> findList();

    @Select("select * from question order by id desc limit #{offset},#{size}")
    List<Question> findListByPage(@Param(value = "offset") int offset, @Param(value = "size") int size);

    @Select("select count(1) from question ")
    int getTotalCount();

    @Select("select * from question where id = #{id}")
    Question getById(String id);

    @Select("select * from question where creator = #{creator} order by id desc limit #{offset},#{size}")
    List<Question> findListById(@Param(value = "creator") int userId, @Param(value = "offset") int offset, @Param(value = "size") int size);

    @Select("select count(1) from question where creator = #{creator}")
    Integer getTotalCountByUser(@Param(value = "creator") int userId);
}
