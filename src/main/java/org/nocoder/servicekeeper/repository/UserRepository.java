package org.nocoder.servicekeeper.repository;

import org.apache.ibatis.annotations.*;
import org.nocoder.servicekeeper.modal.User;

import java.util.List;

@Mapper
public interface UserRepository {


    @Select("SELECT * FROM user")
    List<User> getAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getById(Integer id);

    @Insert("INSERT INTO user(name,ip,port,user,password) VALUES(#{name}, #{ip}, #{port}), #{user}, #{password}")
    void insert(User User);

    @Update("UPDATE user SET name=#{name}, ip=#{ip}, port=#{port}, user=#{user}, password=#{password} WHERE id =#{id}")
    void update(User User);

    @Delete("DELETE FROM User WHERE id =#{id}")
    void delete(Integer id);

}
