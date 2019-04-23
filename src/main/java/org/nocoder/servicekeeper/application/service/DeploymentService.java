package org.nocoder.servicekeeper.application.service;

import org.nocoder.servicekeeper.application.dto.DeploymentPlanDto;
import org.nocoder.servicekeeper.application.dto.ServerServiceMappingDto;

import java.util.List;

/**
 * @author jason
 * @date 2019/4/17.
 */
public interface DeploymentService {
    void executeCommand(Integer serverId, List<String> commandList);

    int add(ServerServiceMappingDto dto);

    int update(ServerServiceMappingDto dto);

    int delete(Integer id);

    List<ServerServiceMappingDto> getAll();

    ServerServiceMappingDto getById(Integer id);

    List<ServerServiceMappingDto> getByServiceId(Integer serviceId);

    List<ServerServiceMappingDto> getByServerId(Integer serverId);

    List<ServerServiceMappingDto> getByServerIdAndServiceId(Integer serverId, Integer serviceId);

    List<DeploymentPlanDto> getDeploymentPlans();
}
