package org.nocoder.servicekeeper.infrastructure.repository;

import org.apache.ibatis.annotations.*;
import org.nocoder.servicekeeper.domain.modal.DeploymentLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jason
 * @date 2019/4/25.
 */
@Mapper
@Repository
public interface DeploymentLogRepository {
    @Insert("insert into deployment_log (service_id, server_id, operation, log_file_path, create_time, operator)" +
            "values(#{serviceId}, #{serverId}, #{operation}, #{logFilePath}, #{createTime}, #{operator})")
    void add(DeploymentLog log);

    @Select("select * from deployment_log")
    List<DeploymentLog> getAll();

    @Select("select * from deployment_log where id=#{id}")
    DeploymentLog getById(@Param("id") Integer id);

    @Select("select max(id) from deployment_log where service_id=#{serviceId} and server_id=#{serverId}")
    Integer getDeploymentLogId(@Param("serviceId") Integer serviceId, @Param("serverId") Integer serverId);

    @Update("update deployment_log set log_file_path = #{logFilePath} where id=#{id}")
    void updateLogFilePath(@Param("id") Integer logId, @Param("logFilePath") String logFilePath);
}
