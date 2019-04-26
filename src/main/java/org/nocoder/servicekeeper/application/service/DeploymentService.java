package org.nocoder.servicekeeper.application.service;

import org.nocoder.servicekeeper.application.dto.DeploymentPlanDto;
import org.nocoder.servicekeeper.application.dto.ServerServiceMappingDto;

import java.util.List;

/**
 * @author jason
 * @date 2019/4/17.
 */
public interface DeploymentService {
    void executeCommand(Integer serviceId, Integer serverId, List<String> commandList) throws Exception;

    int add(ServerServiceMappingDto dto);

    int update(ServerServiceMappingDto dto);

    int updateServiceStatus(Integer serverId, Integer serviceId, String serviceStatus);

    int delete(Integer id);

    List<ServerServiceMappingDto> getAll();

    ServerServiceMappingDto getById(Integer id);

    List<ServerServiceMappingDto> getByServiceId(Integer serviceId);

    List<ServerServiceMappingDto> getByServerId(Integer serverId);

    ServerServiceMappingDto getByServerIdAndServiceId(Integer serverId, Integer serviceId);

    List<DeploymentPlanDto> getDeploymentPlans();
}
