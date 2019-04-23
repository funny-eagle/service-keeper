package org.nocoder.servicekeeper.application.assembler;

import org.nocoder.servicekeeper.application.dto.DeploymentPlanDto;
import org.nocoder.servicekeeper.infrastructure.result.DeploymentPlanResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jason
 * @date 2019/4/19.
 */
@Component
public class DeploymentPlanAssembler {
    public DeploymentPlanDto convertToDto(DeploymentPlanResult result){
        if(result == null){
            return null;
        }
        DeploymentPlanDto dto = new DeploymentPlanDto();
        BeanUtils.copyProperties(result, dto);
        return dto;
    }

    public List<DeploymentPlanDto> convertToDtoList(List<DeploymentPlanResult> resultList){
        if(CollectionUtils.isEmpty(resultList)){
            return Collections.emptyList();
        }
        List<DeploymentPlanDto> dtos = new ArrayList<>();
        resultList.forEach(result -> dtos.add(convertToDto(result)));
        return dtos;
    }
}
