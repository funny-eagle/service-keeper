package org.nocoder.servicekeeper.repository;

import org.apache.ibatis.annotations.*;
import org.nocoder.servicekeeper.modal.Server;

import java.util.List;

@Mapper
public interface ServerRepository {


    @Select("SELECT * FROM server")
    List<Server> getAll();

    @Select("SELECT * FROM server WHERE id = #{id}")
    Server getById(Integer id);

    @Insert("INSERT INTO server(name,ip,port,user,password) VALUES(#{name}, #{ip}, #{port}), #{user}, #{password}")
    void insert(Server server);

    @Update("UPDATE server SET name=#{name}, ip=#{ip}, port=#{port}, user=#{user}, password=#{password} WHERE id =#{id}")
    void update(Server server);

    @Delete("DELETE FROM server WHERE id =#{id}")
    void delete(Integer id);

}
