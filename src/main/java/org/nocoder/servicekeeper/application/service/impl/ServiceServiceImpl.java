package org.nocoder.servicekeeper.application.service.impl;

import org.nocoder.servicekeeper.application.assembler.ServiceAssembler;
import org.nocoder.servicekeeper.application.dto.ServiceDto;
import org.nocoder.servicekeeper.application.service.ServiceService;
import org.nocoder.servicekeeper.domain.modal.Command;
import org.nocoder.servicekeeper.domain.modal.Service;
import org.nocoder.servicekeeper.infrastructure.repository.ServiceRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service service
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

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
    public int insert(ServiceDto dto){
        Service service = assembler.convertToService(dto);
        return serviceRepository.insert(service);
    }

    @Override
    public void executeCommand(Command command) {

    }

    @Override
    public void bindCommand(Service service, Command command) {

    }
}
