package org.nocoder.servicekeeper.application.service.impl;

import org.nocoder.servicekeeper.application.assembler.ServerAssembler;
import org.nocoder.servicekeeper.application.dto.ServerDto;
import org.nocoder.servicekeeper.application.service.ServerService;
import org.nocoder.servicekeeper.common.ssh.Certification;
import org.nocoder.servicekeeper.common.ssh.SshClient;
import org.nocoder.servicekeeper.domain.modal.Server;
import org.nocoder.servicekeeper.infrastructure.repository.ServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * server service impl
 *
 * @author YangJinlong
 */
@Service
public class ServerServiceImpl implements ServerService {

    private Logger logger = LoggerFactory.getLogger(ServerServiceImpl.class);
    private List<ServerDto> serverDtoCacheList = Collections.emptyList();
    @Resource
    private ServerRepository serverRepository;
    @Resource
    private ServerAssembler assembler;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(ServerDto serverDto) {
        Server server = assembler.convertToServer(serverDto);
        int res = serverRepository.insert(server);
        if(res > 0){
            logger.info("insert server success");
            // update server cache list after insert
            this.serverDtoCacheList = assembler.convertToDtoList(serverRepository.getAll());
        }
        return res;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(ServerDto serverDto) {
        Server server = assembler.convertToServer(serverDto);
        int res = serverRepository.update(server);
        if(res > 0){
            logger.info("update server success");
            // update server cache list after update
            this.serverDtoCacheList = assembler.convertToDtoList(serverRepository.getAll());
        }
        return res;
    }

    @Override
    public List<ServerDto> getAllServers() {
        if(CollectionUtils.isEmpty(this.serverDtoCacheList)){
            this.serverDtoCacheList = assembler.convertToDtoList(serverRepository.getAll());
        }
        return this.serverDtoCacheList;
    }

    @Override
    public int delete(int id) {
        int res =  serverRepository.delete(id);
        if(res > 0){
            // update server cache list after delete
            this.serverDtoCacheList = assembler.convertToDtoList(serverRepository.getAll());
        }
        return res;
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
