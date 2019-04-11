package org.nocoder.servicekeeper.application.assembler;

import org.nocoder.servicekeeper.application.dto.ServiceDto;
import org.nocoder.servicekeeper.domain.modal.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * service assembler
 * @author YangJinlong
 */
@Component
public class ServiceAssembler {

    public ServiceDto convertToDto(Service service) {
        if(service == null){
            return null;
        }
        ServiceDto dto = new ServiceDto();
        BeanUtils.copyProperties(service, dto);
        return dto;
    }

    public List<ServiceDto> convertToDtoList(List<Service> serviceList){
        if(CollectionUtils.isEmpty(serviceList)){
            return Collections.emptyList();
        }
        List<ServiceDto> serviceDtoList = new ArrayList<>();
        serviceList.forEach(service -> serviceDtoList.add(convertToDto(service)));
        return serviceDtoList;
    }
}
