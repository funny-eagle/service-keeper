package org.nocoder.servicekeeper.application.service.impl;

import org.nocoder.servicekeeper.application.assembler.DeploymentLogAssembler;
import org.nocoder.servicekeeper.application.dto.DeploymentLogDto;
import org.nocoder.servicekeeper.application.service.DeploymentLogService;
import org.nocoder.servicekeeper.domain.modal.DeploymentLog;
import org.nocoder.servicekeeper.infrastructure.repository.DeploymentLogRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jason
 * @date 2019/4/25.
 */
@Service
public class DeploymentLogServiceImpl implements DeploymentLogService {

    @Resource
    private DeploymentLogRepository repository;
    @Resource
    private DeploymentLogAssembler assembler;

    @Override
    public void add(DeploymentLogDto dto) {
        DeploymentLog log = assembler.convertToDeploymentLog(dto);
        repository.add(log);
    }
}
