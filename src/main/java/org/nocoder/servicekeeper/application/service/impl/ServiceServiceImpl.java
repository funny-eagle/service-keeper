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
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * Service service
 *
 * @author jason
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private List<ServiceDto> serviceDtoCacheList = Collections.emptyList();

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
        if (CollectionUtils.isEmpty(this.serviceDtoCacheList)) {
            reloadServiceDtoCacheList();
        }
        return this.serviceDtoCacheList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(ServiceDto dto) {
        dto.setCreateTime(DateTimeUtils.getCurrentDateTime());
        Service service = assembler.convertToService(dto);
        int res = serviceRepository.insert(service);
        if (res > 0) {
            logger.info("insert service success");
            // update the service cache list after insert
            reloadServiceDtoCacheList();
        }
        return res;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(ServiceDto dto) {
        dto.setUpdateTime(DateTimeUtils.getCurrentDateTime());
        Service service = assembler.convertToService(dto);
        int res = serviceRepository.update(service);
        if (res > 0) {
            logger.info("update service success");
            // update the service cache list after insert
            reloadServiceDtoCacheList();
        }
        return res;
    }

    private void reloadServiceDtoCacheList() {
        this.serviceDtoCacheList = assembler.convertToDtoList(serviceRepository.getAll());
    }

    @Override
    public int delete(Integer id) {
        int res = serviceRepository.delete(id);
        if (res > 0) {
            if (res > 0) {
                logger.info("delete service success");
                // update the service cache list after delte
                reloadServiceDtoCacheList();
            }
        }
        return res;
    }

}
