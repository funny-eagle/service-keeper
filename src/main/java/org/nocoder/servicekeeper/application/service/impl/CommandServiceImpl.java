package org.nocoder.servicekeeper.application.service.impl;

import org.nocoder.servicekeeper.application.assembler.CommandAssembler;
import org.nocoder.servicekeeper.application.dto.CommandDto;
import org.nocoder.servicekeeper.application.service.CommandService;
import org.nocoder.servicekeeper.domain.modal.Command;
import org.nocoder.servicekeeper.infrastructure.repository.CommandRepository;

import javax.annotation.Resource;
import java.util.List;

public class CommandServiceImpl implements CommandService {

    @Resource
    private CommandRepository repository;

    @Resource
    private CommandAssembler assembler;

    @Override
    public int insert(CommandDto dto) {
        Command command = assembler.convertToCommand(dto);
        return repository.insert(command);
    }

    @Override
    public int update(CommandDto dto) {
        Command command = assembler.convertToCommand(dto);
        return repository.update(command);
    }

    @Override
    public int delete(int id) {
        return repository.delete(id);
    }

    @Override
    public List<CommandDto> getAll() {
        List<Command> commandList = repository.getAll();
        return assembler.convertToDtoList(commandList);
    }

    @Override
    public CommandDto getById(int id) {
        Command command = repository.getById(id);
        CommandDto dto = assembler.convertToDto(command);
        return dto;
    }
}
