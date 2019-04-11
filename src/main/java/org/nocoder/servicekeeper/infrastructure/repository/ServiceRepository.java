package org.nocoder.servicekeeper.infrastructure.repository;

import org.apache.ibatis.annotations.*;
import org.nocoder.servicekeeper.domain.modal.Service;

import java.util.List;

@Mapper
public interface ServiceRepository {


    @Select("SELECT * FROM service")
    List<Service> getAll();

    @Select("SELECT * FROM service WHERE id = #{id}")
    Service getById(Integer id);

    @Insert("INSERT INTO service")
    int insert(Service service);

    @Update("UPDATE service SET name=#{name} WHERE id =#{id}")
    int update(Service service);

    @Delete("DELETE FROM service WHERE id =#{id}")
    int delete(Integer id);

}
