package git.wangwangyuwan.demo.mapper;

import git.wangwangyuwan.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) " +
            "values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void addUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(String token);
}
