package org.nocoder.servicekeeper.application.service;

import org.nocoder.servicekeeper.application.dto.ServiceDto;
import org.nocoder.servicekeeper.domain.modal.Command;
import org.nocoder.servicekeeper.domain.modal.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceService {
    ServiceDto getById(int id);

    List<ServiceDto> getAll();

    @Transactional(rollbackFor = Exception.class)
    int insert(ServiceDto dto);

    @Transactional(rollbackFor = Exception.class)
    int update(ServiceDto dto);

    @Transactional(rollbackFor = Exception.class)
    int updateServiceStatus(Integer id, String status);

    /**
     * execute command
     * @param command
     */
    void executeCommand(Integer id, Command command);

    void bindCommand(Service service, Command command);
}
