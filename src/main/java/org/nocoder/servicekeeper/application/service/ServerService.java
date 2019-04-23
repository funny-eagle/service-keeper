package org.nocoder.servicekeeper.application.service;

import org.nocoder.servicekeeper.application.dto.ServerDto;

import java.util.List;

/**
 * @author jason
 */
public interface ServerService {

    /**
     * add server
     *
     * @param serverDto
     * @return
     */
    int insert(ServerDto serverDto);

    /**
     * get all servers
     *
     * @return
     */
    List<ServerDto> getAllServers();

    /**
     * delete by id
     *
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * get server by id
     *
     * @param id
     * @return
     */
    ServerDto getById(Integer id);

    /**
     * update server
     *
     * @param serverDto
     * @return
     */
    int update(ServerDto serverDto);

    /**
     * test ssh connection
     *
     * @param serverDto
     * @return
     */
    List<String> testConnection(ServerDto serverDto);
}
