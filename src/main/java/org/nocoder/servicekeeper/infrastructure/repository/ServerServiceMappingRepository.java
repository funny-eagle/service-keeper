package org.nocoder.servicekeeper.infrastructure.repository;

import org.apache.ibatis.annotations.*;
import org.nocoder.servicekeeper.domain.modal.Server;
import org.nocoder.servicekeeper.domain.modal.ServerServiceMapping;
import org.nocoder.servicekeeper.infrastructure.result.DeploymentPlanResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * server service mapping repository
 *
 * @author jason
 */
@Mapper
@Repository
public interface ServerServiceMappingRepository {

    @Select("SELECT * FROM server_service_mapping")
    @Results({
            @Result(column = "server_id", property = "serverId"),
            @Result(column = "service_id", property = "serviceId"),
            @Result(column = "service_status", property = "serviceStatus"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),

    })
    List<ServerServiceMapping> getAll();

    @Select("SELECT * FROM server_service_mapping WHERE id = #{id}")
    @Results({
            @Result(column = "server_id", property = "serverId"),
            @Result(column = "service_id", property = "serviceId"),
            @Result(column = "service_status", property = "serviceStatus"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),

    })
    ServerServiceMapping getById(Integer id);

    @Select("select * from server_service_mapping where server_id=#{serverId}")
    @Results({
            @Result(column = "server_id", property = "serverId"),
            @Result(column = "service_id", property = "serviceId"),
            @Result(column = "service_status", property = "serviceStatus"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),

    })
    List<ServerServiceMapping> getByServerId(Integer serverId);

    @Select("select * from server_service_mapping where service_id=#{serviceId}")
    @Results({
            @Result(column = "server_id", property = "serverId"),
            @Result(column = "service_id", property = "serviceId"),
            @Result(column = "service_status", property = "serviceStatus"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),

    })
    List<ServerServiceMapping> getByServiceId(Integer serverId);

    @Insert("insert into server_service_mapping ( service_id, " +
            " service_status ,  server_id ,  create_time , update_time, operator ) " +
            "values ( #{serviceId}, #{serviceStatus}, #{serverId}, #{createTime}, #{updateTime}, #{operator});")
    int insert(ServerServiceMapping mapping);

    @Update("update server_service_mapping set update_time =#{updateTime}," +
            " service_id =#{serviceId}, service_status =#{serviceStatus}, server_id =#{serverId},  " +
            " operator =#{operator} where id=#{id}")
    int update(ServerServiceMapping mapping);

    @Delete("DELETE FROM server_service_mapping WHERE id =#{id}")
    int delete(Integer id);


    @Select("select * from server_service_mapping where server_id=#{serverId} and  service_id=#{serviceId}")
    @Results({
            @Result(column = "server_id", property = "serverId"),
            @Result(column = "service_id", property = "serviceId"),
            @Result(column = "service_status", property = "serviceStatus"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    List<ServerServiceMapping> getByServerIdAndServiceId(@Param("serverId") Integer serverId, @Param("serviceId") Integer serviceId);

    @Select("select a.name as serviceName, a.port as servicePort, " +
            "a.docker_image_name, a.docker_image_tag, a.docker_container_name," +
            "b.name as serverName, b.ip as serverIp, c.* " +
            "from service a, server b, server_service_mapping c " +
            "where a.id = c.service_id and b.id=c.server_id;")
    @Results({
            @Result(column = "server_id", property = "serverId"),
            @Result(column = "service_id", property = "serviceId"),
            @Result(column = "service_status", property = "serviceStatus"),
            @Result(column = "docker_image_name", property = "dockerImageName"),
            @Result(column = "docker_image_tag", property = "dockerImageTag"),
            @Result(column = "docker_container_name", property = "dockerContainerName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    List<DeploymentPlanResult> getDeploymentPlans();
}
