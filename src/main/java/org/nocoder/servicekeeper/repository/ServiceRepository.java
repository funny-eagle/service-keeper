package org.nocoder.servicekeeper.repository;

import org.apache.ibatis.annotations.*;
import org.nocoder.servicekeeper.modal.Service;

import java.util.List;

@Mapper
public interface ServiceRepository {


    @Select("SELECT * FROM service")
    List<Service> getAll();

    @Select("SELECT * FROM service WHERE id = #{id}")
    Service getById(Integer id);

    @Insert("INSERT INTO service")
    void insert(Service service);

    @Update("UPDATE service SET name=#{name} WHERE id =#{id}")
    void update(Service service);

    @Delete("DELETE FROM service WHERE id =#{id}")
    void delete(Integer id);

}
