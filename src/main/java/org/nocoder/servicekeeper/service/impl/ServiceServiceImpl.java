package org.nocoder.servicekeeper.service.impl;

import org.nocoder.servicekeeper.modal.Service;
import org.nocoder.servicekeeper.repository.ServiceRepository;
import org.nocoder.servicekeeper.service.ServiceService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service service
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Resource
    private ServiceRepository serviceRepository;

    @Override
    public Service getById(int id) {
        return serviceRepository.getById(id);
    }

    @Override
    public List<Service> getAll(){
        return serviceRepository.getAll();
    }
}
