package org.nocoder.servicekeeper.application.service.impl;

import org.nocoder.servicekeeper.application.assembler.ServiceAssembler;
import org.nocoder.servicekeeper.application.dto.ServiceDto;
import org.nocoder.servicekeeper.application.service.ServiceService;
import org.nocoder.servicekeeper.domain.modal.Command;
import org.nocoder.servicekeeper.domain.modal.Service;
import org.nocoder.servicekeeper.infrastructure.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Service service
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);

    @Resource
    private ServiceAssembler assembler;

    @Resource
    private ServiceRepository serviceRepository;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

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
    @Transactional(rollbackFor = Exception.class)
    public int update(ServiceDto dto){
        Service service = assembler.convertToService(dto);
        return serviceRepository.update(service);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateServiceStatus(Integer id, String status){
        return serviceRepository.updateServiceStatus(id, status);
    }


    @Override
    public void executeCommand(Integer id, Command command) {
        threadPoolExecutor.execute(()->{
            logger.info("start to execute command");
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            serviceRepository.updateServiceStatus(id, "RUNNING");
            logger.info("execute command finished!");
        });
    }

    @Override
    public void bindCommand(Service service, Command command) {

    }
}
