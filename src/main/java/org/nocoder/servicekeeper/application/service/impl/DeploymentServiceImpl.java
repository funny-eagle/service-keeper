package org.nocoder.servicekeeper.application.service.impl;

import org.nocoder.servicekeeper.application.assembler.ServerServiceMappingAssembler;
import org.nocoder.servicekeeper.application.dto.ServerServiceMappingDto;
import org.nocoder.servicekeeper.application.service.DeploymentService;
import org.nocoder.servicekeeper.common.Enumeration.ServiceStatus;
import org.nocoder.servicekeeper.common.ssh.Certification;
import org.nocoder.servicekeeper.common.ssh.SshClient;
import org.nocoder.servicekeeper.common.util.DateTimeUtils;
import org.nocoder.servicekeeper.domain.modal.Server;
import org.nocoder.servicekeeper.domain.modal.ServerServiceMapping;
import org.nocoder.servicekeeper.infrastructure.repository.ServerRepository;
import org.nocoder.servicekeeper.infrastructure.repository.ServerServiceMappingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jason
 * @date 2019/4/17.
 */
@Service
public class DeploymentServiceImpl implements DeploymentService {
    private Logger logger = LoggerFactory.getLogger(DeploymentServiceImpl.class);
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    @Resource
    private ServerRepository serverRepository;
    @Resource
    private ServerServiceMappingRepository serverServiceMappingRepository;
    @Resource
    private ServerServiceMappingAssembler assembler;

    @Override
    public void executeCommand(Integer serverId, List<String> commandList) {
        threadPoolExecutor.execute(() -> {
            Server server = serverRepository.getById(serverId);
            logger.info("start to execute command...");
            Certification certification = new Certification();
            certification.setHost(server.getIp());
            certification.setPort(Integer.parseInt(server.getPort()));
            certification.setUser(server.getUser());
            certification.setPassword(server.getPassword());
            List<String> resultList = SshClient.execCommands(certification, commandList);
            logger.info("execute command finished!");
        });
    }


    @Override
    public int insert(ServerServiceMappingDto dto) {
        dto.setServiceStatus(ServiceStatus.STOP.status());
        dto.setCreateTime(DateTimeUtils.getCurrentDateTime());
        ServerServiceMapping mapping = assembler.convertToMapping(dto);
        return serverServiceMappingRepository.insert(mapping);
    }

    @Override
    public int update(ServerServiceMappingDto dto) {
        dto.setUpdateTime(DateTimeUtils.getCurrentDateTime());
        ServerServiceMapping mapping = assembler.convertToMapping(dto);
        return serverServiceMappingRepository.update(mapping);
    }

    @Override
    public int delete(Integer id) {
        return serverServiceMappingRepository.delete(id);
    }

    @Override
    public List<ServerServiceMappingDto> selectAll() {
        List<ServerServiceMapping> mappings =  serverServiceMappingRepository.getAll();
        return assembler.convertToDtoList(mappings);
    }

    @Override
    public ServerServiceMappingDto selectById(Integer id) {
        return assembler.convertToDto(serverServiceMappingRepository.getById(id));
    }

    @Override
    public List<ServerServiceMappingDto> selectByServiceId(Integer serviceId) {
        return assembler.convertToDtoList(serverServiceMappingRepository.getByServiceId(serviceId));
    }

    @Override
    public List<ServerServiceMappingDto> selectByServerId(Integer serverId) {
        return assembler.convertToDtoList(serverServiceMappingRepository.getByServerId(serverId));
    }

    @Override
    public List<ServerServiceMappingDto> selectByServerIdAndServiceId(Integer serverId, Integer serviceId) {
        return assembler.convertToDtoList(serverServiceMappingRepository.getByServerIdAndServiceId(serverId, serviceId));
    }
}
