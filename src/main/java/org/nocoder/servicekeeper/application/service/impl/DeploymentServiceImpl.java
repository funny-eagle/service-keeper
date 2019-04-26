package org.nocoder.servicekeeper.application.service.impl;

import org.nocoder.servicekeeper.application.assembler.DeploymentPlanAssembler;
import org.nocoder.servicekeeper.application.assembler.ServerServiceMappingAssembler;
import org.nocoder.servicekeeper.application.dto.DeploymentPlanDto;
import org.nocoder.servicekeeper.application.dto.ServerServiceMappingDto;
import org.nocoder.servicekeeper.application.observer.DeploymentLogObserver;
import org.nocoder.servicekeeper.application.observer.DeploymentMessage;
import org.nocoder.servicekeeper.application.observer.DeploymentSubject;
import org.nocoder.servicekeeper.application.service.DeploymentService;
import org.nocoder.servicekeeper.common.enumeration.ServiceStatus;
import org.nocoder.servicekeeper.common.ssh.Certification;
import org.nocoder.servicekeeper.common.ssh.SshClient;
import org.nocoder.servicekeeper.common.util.DateTimeUtils;
import org.nocoder.servicekeeper.domain.modal.DeploymentLog;
import org.nocoder.servicekeeper.domain.modal.Server;
import org.nocoder.servicekeeper.domain.modal.ServerServiceMapping;
import org.nocoder.servicekeeper.infrastructure.repository.DeploymentLogRepository;
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
    private ServerServiceMappingRepository repository;
    @Resource
    private ServerServiceMappingAssembler assembler;
    @Resource
    private DeploymentLogRepository deploymentLogRepository;

    @Resource
    private DeploymentPlanAssembler deploymentPlanAssembler;

    @Override
    public void executeCommand(Integer serviceId, Integer serverId, List<String> commandList) throws Exception {
        deploymentLogRepository.add(createDeploymentLog(serviceId, serverId));
        threadPoolExecutor.execute(() -> {
            Server server = serverRepository.getById(serverId);
            logger.info("start to execute deploymentMessage...");
            Certification certification = getCertification(server);
            List<String> resultList = SshClient.execCommands(certification, commandList);

            DeploymentSubject subject = new DeploymentSubject();
            new DeploymentLogObserver(subject);
            DeploymentMessage message = new DeploymentMessage();
            message.setDeploymentLogId(deploymentLogRepository.getDeploymentLogId(serviceId, serverId));
            message.setServiceId(serviceId);
            message.setServerId(serverId);
            message.setCommandList(commandList);
            message.setResultList(resultList);
            subject.setDeploymentMessage(message);
            logger.info("execute deploymentMessage finished!");
        });
    }

    private DeploymentLog createDeploymentLog(Integer serviceId, Integer serverId) {
        DeploymentLog log = new DeploymentLog();
        log.setServiceId(serviceId);
        log.setServerId(serverId);
        log.setOperation("Deploy Latest Image");
        log.setOperator("YangJinlong");
        log.setCreateTime(DateTimeUtils.getCurrentDateTime());
        return log;
    }

    private Certification getCertification(Server server) {
        Certification certification = new Certification();
        certification.setHost(server.getIp());
        certification.setPort(Integer.parseInt(server.getPort()));
        certification.setUser(server.getUser());
        certification.setPassword(server.getPassword());
        return certification;
    }


    @Override
    public int add(ServerServiceMappingDto dto) {
        dto.setServiceStatus(ServiceStatus.STOPPED.status());
        dto.setCreateTime(DateTimeUtils.getCurrentDateTime());
        ServerServiceMapping mapping = assembler.convertToMapping(dto);
        return repository.insert(mapping);
    }

    @Override
    public int update(ServerServiceMappingDto dto) {
        dto.setUpdateTime(DateTimeUtils.getCurrentDateTime());
        ServerServiceMapping mapping = assembler.convertToMapping(dto);
        return repository.update(mapping);
    }

    @Override
    public int updateServiceStatus(Integer serverId, Integer serviceId, String serviceStatus) {
        return repository.updateServiceStatus(serverId, serviceId, serviceStatus);
    }

    @Override
    public int delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public List<ServerServiceMappingDto> getAll() {
        List<ServerServiceMapping> mappings = repository.getAll();
        return assembler.convertToDtoList(mappings);
    }

    @Override
    public ServerServiceMappingDto getById(Integer id) {
        return assembler.convertToDto(repository.getById(id));
    }

    @Override
    public List<ServerServiceMappingDto> getByServiceId(Integer serviceId) {
        return assembler.convertToDtoList(repository.getByServiceId(serviceId));
    }

    @Override
    public List<ServerServiceMappingDto> getByServerId(Integer serverId) {
        return assembler.convertToDtoList(repository.getByServerId(serverId));
    }

    @Override
    public ServerServiceMappingDto getByServerIdAndServiceId(Integer serverId, Integer serviceId) {
        return assembler.convertToDto(repository.getByServerIdAndServiceId(serverId, serviceId));
    }

    @Override
    public List<DeploymentPlanDto> getDeploymentPlans() {
        return deploymentPlanAssembler.convertToDtoList(repository.getDeploymentPlans());
    }
}
