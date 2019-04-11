package org.nocoder.servicekeeper.infrastructure.repository;

import org.apache.ibatis.annotations.*;
import org.nocoder.servicekeeper.domain.modal.Command;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommandRepository {
    @Insert("insert into command (name) values(#{name})")
    int insert(Command command);

    @Update("update command set command=#{command} where id=#{id}")
    int update(Command command);

    @Delete("delete from command where id=#{id}")
    int delete(int id);

    @Select("select * from command")
    List<Command> getAll();

    @Select("select * from command where id = #{id}")
    Command getById(int id);
}
