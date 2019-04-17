package org.nocoder.servicekeeper.infrastructure.repository;

import org.apache.ibatis.annotations.*;
import org.nocoder.servicekeeper.domain.modal.Server;
import org.nocoder.servicekeeper.domain.modal.ServerServiceMapping;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * server service mapping repository
 * @author jason
 */
@Mapper
@Repository
public interface ServerServiceMappingRepository {



    @Select("SELECT * FROM server_service_mapping")
    @Results({
            @Result(column = "server_id", property = "serverId"),
            @Result(column = "service_id", property = "serviceId"),
            @Result(column = "service_alias", property = "serviceAlias"),
            @Result(column = "service_status", property = "serviceStatus"),
            @Result(column = "server_name", property = "serverName"),
            @Result(column = "server_ip", property = "serverIp"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),

    })
    List<ServerServiceMapping> getAll();

    @Select("SELECT * FROM server_service_mapping WHERE id = #{id}")
    @Results({
            @Result(column = "server_id", property = "serverId"),
            @Result(column = "service_id", property = "serviceId"),
            @Result(column = "service_alias", property = "serviceAlias"),
            @Result(column = "service_status", property = "serviceStatus"),
            @Result(column = "server_name", property = "serverName"),
            @Result(column = "server_ip", property = "serverIp"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),

    })
    Server getById(Integer id);

    @Insert("insert into server_service_mapping ( server_ip, service_alias, service_id, " +
            "server_name ,  service_status ,  server_id ,  create_time , update_time, operator ) " +
            "values ( #{serverIp}, #{serviceAlias}, #{serviceId}, " +
            "#{serverName}, #{serviceStatus}, #{serverId}, #{createTime}, #{updateTime}, #{operator});")
    int insert(ServerServiceMapping mapping);

    @Update("update server_service_mapping set update_time =#{updateTime},  server_ip =#{serverIp}," +
            " service_alias =#{serviceAlias}, service_id =#{serviceId},  server_name =#{serverName},  " +
            "service_status =#{serviceStatus}, server_id =#{serverId},  " +
            "operator =#{operator} where id=#{id}")
    int update(ServerServiceMapping mapping);

    @Delete("DELETE FROM server_service_mapping WHERE id =#{id}")
    int delete(Integer id);

}
