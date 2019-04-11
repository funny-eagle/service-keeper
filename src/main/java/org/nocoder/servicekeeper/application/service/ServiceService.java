package org.nocoder.servicekeeper.application.service;

import org.nocoder.servicekeeper.application.dto.ServiceDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceService {
    ServiceDto getById(int id);

    List<ServiceDto> getAll();

    @Transactional(rollbackFor = Exception.class)
    int insert(ServiceDto dto);
}
