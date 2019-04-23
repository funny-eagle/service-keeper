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
    void insert(User user);

    @Update("UPDATE user SET name=#{name}, ip=#{ip}, port=#{port}, user=#{user}, password=#{password} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM User WHERE id =#{id}")
    void delete(Integer id);

}
