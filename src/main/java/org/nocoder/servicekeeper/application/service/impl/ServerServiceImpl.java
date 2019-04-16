package org.nocoder.servicekeeper.application.service.impl;

import org.nocoder.servicekeeper.application.assembler.ServerAssembler;
import org.nocoder.servicekeeper.application.dto.ServerDto;
import org.nocoder.servicekeeper.application.service.ServerService;
import org.nocoder.servicekeeper.common.ssh.Certification;
import org.nocoder.servicekeeper.common.ssh.SshClient;
import org.nocoder.servicekeeper.domain.modal.Server;
import org.nocoder.servicekeeper.infrastructure.repository.ServerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * server service impl
 *
 * @author YangJinlong
 */
@Service
public class ServerServiceImpl implements ServerService {
    @Resource
    private ServerRepository serverRepository;
    @Resource
    private ServerAssembler assembler;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(ServerDto serverDto) {
        Server server = assembler.convertToServer(serverDto);
        return serverRepository.insert(server);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(ServerDto serverDto) {
        Server server = assembler.convertToServer(serverDto);
        return serverRepository.update(server);
    }

    @Override
    public List<ServerDto> getAllServers() {
        List<Server> serverList = serverRepository.getAll();
        List<ServerDto> serverDtoList = assembler.convertToDtoList(serverList);
        return serverDtoList;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public ServerDto getById(Integer id) {
        Server server = serverRepository.getById(id);
        return assembler.convertToDto(server);
    }

    @Override
    public List<String> testConnection(ServerDto serverDto) {
        Certification certification = new Certification();
        certification.setHost(serverDto.getIp());
        certification.setPort(Integer.parseInt(serverDto.getPort()));
        certification.setUser(serverDto.getUser());
        certification.setPassword(serverDto.getPassword());
        List<String> result = SshClient.execCommands(certification, Arrays.asList("df -h"));
        return result;
    }
}
