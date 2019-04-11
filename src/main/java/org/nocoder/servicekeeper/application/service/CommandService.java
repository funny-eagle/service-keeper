package org.nocoder.servicekeeper.application.service;

import org.nocoder.servicekeeper.application.dto.CommandDto;

import java.util.List;

public interface CommandService {

    int insert(CommandDto dto);

    int update(CommandDto dto);

    int delete(int id);

    List<CommandDto> getAll();

    CommandDto getById(int id);
}
