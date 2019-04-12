package org.nocoder.servicekeeper.infrastructure.repository;

import org.apache.ibatis.annotations.*;
import org.nocoder.servicekeeper.domain.modal.Service;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ServiceRepository {


    @Select("SELECT * FROM service")
    @Results({
            @Result(column = "docker_image_name", property = "dockerImageName"),
            @Result(column = "docker_image_tag", property = "dockerImageTag"),
            @Result(column = "docker_container_name", property = "dockerContainerName"),
            @Result(column = "docker_pull_command", property = "dockerPullCommand"),
            @Result(column = "docker_run_command", property = "dockerRunCommand"),
            @Result(column = "docker_start_command", property = "dockerStartCommand"),
            @Result(column = "docker_restart_command", property = "dockerRestartCommand"),
            @Result(column = "docker_stop_command", property = "dockerStopCommand"),
            @Result(column = "docker_container_name", property = "dockerContainerName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    List<Service> getAll();

    @Select("SELECT * FROM service WHERE id = #{id}")
    @Results({
            @Result(column = "docker_image_name", property = "dockerImageName"),
            @Result(column = "docker_image_tag", property = "dockerImageTag"),
            @Result(column = "docker_container_name", property = "dockerContainerName"),
            @Result(column = "docker_pull_command", property = "dockerPullCommand"),
            @Result(column = "docker_run_command", property = "dockerRunCommand"),
            @Result(column = "docker_start_command", property = "dockerStartCommand"),
            @Result(column = "docker_restart_command", property = "dockerRestartCommand"),
            @Result(column = "docker_stop_command", property = "dockerStopCommand"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
    })
    Service getById(Integer id);

    @Insert("INSERT INTO service (name, ip, port, docker_image_name, docker_image_tag, docker_container_name, " +
            "docker_pull_command, docker_run_command, docker_start_command, docker_restart_command, " +
            "docker_stop_command, status,create_time, update_time) " +
            "VALUES (#{name}, #{ip}, #{port}, #{dockerImageName}, #{dockerImageTag}, #{dockerContainerName}, " +
            "#{dockerPullCommand}, #{dockerRunCommand}, #{dockerStartCommand}, #{dockerRestartCommand}, #{dockerStopCommand}," +
            "#{status}, #{createTime}, #{updateTime})")
    int insert(Service service);

    @Update("UPDATE service SET ip =#{ip},  port =#{port}, docker_image_name =#{dockerImageName}, " +
            " docker_image_tag =#{dockerImageTag},  docker_container_name =#{dockerContainerName}, " +
            " docker_pull_command =#{dockerPullCommand},  docker_run_command =#{dockerRunCommand}, " +
            " docker_start_command =#{dockerStartCommand},  docker_stop_command =#{dockerStopCommand}, " +
            " docker_restart_command =#{dockerRestartCommand},  status =#{status},  create_time =#{createTime}, " +
            " update_time =#{updateTime} where id=#{id}")
    int update(Service service);

    @Delete("DELETE FROM service WHERE id =#{id}")
    int delete(Integer id);

    @Update("update service set status=#{status} where id=#{id}")
    int updateServiceStatus(@Param("id") Integer id, @Param("status") String status);
}
