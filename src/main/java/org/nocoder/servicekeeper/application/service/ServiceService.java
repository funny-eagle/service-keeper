package org.nocoder.servicekeeper.application.service;

import org.nocoder.servicekeeper.application.dto.ServiceDto;

import java.util.List;

public interface ServiceService {
    ServiceDto getById(int id);

    List<ServiceDto> getAll();
}
