package org.nocoder.servicekeeper.application.assembler;

import org.nocoder.servicekeeper.application.dto.ServerServiceMappingDto;
import org.nocoder.servicekeeper.domain.modal.ServerServiceMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jason
 * @date 2019/4/17.
 */
@Component
public class ServerServiceMappingAssembler {

    public ServerServiceMappingDto convertToDto(ServerServiceMapping mapping){
        ServerServiceMappingDto dto = new ServerServiceMappingDto();
        BeanUtils.copyProperties(mapping, dto);
        return dto;
    }

    public List<ServerServiceMappingDto> convertToDtoList(List<ServerServiceMapping> mappings){
        if(CollectionUtils.isEmpty(mappings)){
            return Collections.emptyList();
        }
        List<ServerServiceMappingDto> dtoList = new ArrayList<>();
        mappings.forEach(mapping -> dtoList.add(convertToDto(mapping)));
        return dtoList;
    }
}
