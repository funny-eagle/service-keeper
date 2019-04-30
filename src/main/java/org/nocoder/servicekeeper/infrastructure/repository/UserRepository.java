package org.nocoder.servicekeeper.infrastructure.repository;

import org.apache.ibatis.annotations.*;
import org.nocoder.servicekeeper.domain.modal.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRepository {


    @Select("SELECT * FROM user")
    List<User> getAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getById(Integer id);

    @Insert("INSERT INTO user(name,ip,port,user,password) VALUES(#{name}, #{ip}, #{port}), #{user}, #{password}")
    int insert(User user);

    @Update("UPDATE user SET name=#{name}, password=#{password}, last_sign_in_time=#{lastSignInTime}, last_sign_in_ip=#{lastSignInIp} WHERE id =#{id}")
    int update(User user);

    @Delete("DELETE FROM User WHERE id = #{id}")
    int delete(Integer id);

    @Select("SELECT * FROM user WHERE name=#{name} and password=#{password}")
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "last_sign_in_time", property = "lastSignInTime"),
            @Result(column = "last_sign_in_ip", property = "lastSignInIp")
    })
    User getByUsernameAndPassword(@Param("name") String username, @Param("password") String password);
}
