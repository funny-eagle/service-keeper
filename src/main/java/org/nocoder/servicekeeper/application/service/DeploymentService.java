package org.nocoder.servicekeeper.application.service;

import org.nocoder.servicekeeper.application.dto.ServerServiceMappingDto;

import java.util.List;

/**
 * @author jason
 * @date 2019/4/17.
 */
public interface DeploymentService {
    void executeCommand(Integer serverId, List<String> commandList);

    int insert(ServerServiceMappingDto dto);

    int update(ServerServiceMappingDto dto);

    int delete(Integer id);

    List<ServerServiceMappingDto> selectAll();

    ServerServiceMappingDto selectById(Integer id);

    List<ServerServiceMappingDto> selectByServiceId(Integer serviceId);

    List<ServerServiceMappingDto> selectByServerId(Integer serverId);

    List<ServerServiceMappingDto> selectByServerIdAndServiceId(Integer serverId, Integer serviceId);
}
