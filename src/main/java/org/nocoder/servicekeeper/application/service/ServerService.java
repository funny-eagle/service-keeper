package org.nocoder.servicekeeper.application.service;

import org.nocoder.servicekeeper.application.dto.ServerDto;

import java.util.List;

public interface ServerService {

    int insert(ServerDto serverDto);
    List<ServerDto> getAllServers();
    int delete(int id);

    ServerDto getById(Integer id);

    int update(ServerDto serverDto);
}
