package org.nocoder.servicekeeper.application.assembler;

import org.nocoder.servicekeeper.application.dto.DeploymentLogDto;
import org.nocoder.servicekeeper.domain.modal.DeploymentLog;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jason
 * @date 2019/4/25.
 */
@Component
public class DeploymentLogAssembler {

    public DeploymentLog convertToDeploymentLog(DeploymentLogDto dto) {
        if (dto == null) {
            return null;
        }

        DeploymentLog log = new DeploymentLog();
        BeanUtils.copyProperties(dto, log);
        return log;
    }

    public DeploymentLogDto convertToDto(DeploymentLog log) {
        if (log == null) {
            return null;
        }

        DeploymentLogDto dto = new DeploymentLogDto();
        BeanUtils.copyProperties(log, dto);
        return dto;
    }

    public List<DeploymentLogDto> convertToDtoList(List<DeploymentLog> logList) {
        if (CollectionUtils.isEmpty(logList)) {
            return Collections.emptyList();
        }
        List<DeploymentLogDto> dtoList = new ArrayList<>();
        logList.forEach(log -> dtoList.add(convertToDto(log)));
        return dtoList;
    }
}
