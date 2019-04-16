package org.nocoder.servicekeeper.application.assembler;

import org.nocoder.servicekeeper.application.dto.ServerDto;
import org.nocoder.servicekeeper.domain.modal.Server;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * server assembler
 *
 * @author YangJinlong
 */
@Component
public class ServerAssembler {
    public Server convertToServer(ServerDto serverDto) {
        if (serverDto == null) {
            return null;
        }
        Server server = new Server();
        BeanUtils.copyProperties(serverDto, server);
        return server;
    }

    public ServerDto convertToDto(Server server) {
        if (server == null) {
            return null;
        }

        ServerDto dto = new ServerDto();
        BeanUtils.copyProperties(server, dto);
        return dto;
    }

    public List<ServerDto> convertToDtoList(List<Server> serverList) {
        if (CollectionUtils.isEmpty(serverList)) {
            return Collections.emptyList();
        }
        List<ServerDto> dtoList = new ArrayList<>();
        serverList.forEach(server -> dtoList.add(convertToDto(server)));
        return dtoList;
    }
}
