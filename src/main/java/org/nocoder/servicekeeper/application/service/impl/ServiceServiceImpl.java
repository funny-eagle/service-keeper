package org.nocoder.servicekeeper.application.service.impl;

import org.nocoder.servicekeeper.application.assembler.ServiceAssembler;
import org.nocoder.servicekeeper.application.dto.ServiceDto;
import org.nocoder.servicekeeper.application.service.ServiceService;
import org.nocoder.servicekeeper.common.util.DateTimeUtils;
import org.nocoder.servicekeeper.domain.modal.Service;
import org.nocoder.servicekeeper.infrastructure.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service service
 *
 * @author jason
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);

    @Resource
    private ServiceAssembler assembler;

    @Resource
    private ServiceRepository serviceRepository;


    @Override
    public ServiceDto getById(int id) {
        Service service = serviceRepository.getById(id);
        return assembler.convertToDto(service);
    }

    @Override
    public List<ServiceDto> getAll() {
        List<Service> serviceList = serviceRepository.getAll();
        return assembler.convertToDtoList(serviceList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(ServiceDto dto) {
        dto.setCreateTime(DateTimeUtils.getCurrentDateTime());
        Service service = assembler.convertToService(dto);
        return serviceRepository.insert(service);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(ServiceDto dto) {
        dto.setUpdateTime(DateTimeUtils.getCurrentDateTime());
        Service service = assembler.convertToService(dto);
        return serviceRepository.update(service);
    }

}
