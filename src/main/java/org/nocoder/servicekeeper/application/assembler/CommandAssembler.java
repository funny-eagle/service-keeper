package org.nocoder.servicekeeper.application.assembler;

import org.nocoder.servicekeeper.application.dto.CommandDto;
import org.nocoder.servicekeeper.domain.modal.Command;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * command assembler
 * @author YangJinlong
 */
@Component
public class CommandAssembler {
    public Command convertToCommand(CommandDto dto) {
        if(dto==null){
            return null;
        }
        Command command = new Command();
        BeanUtils.copyProperties(dto, command);
        return command;
    }

    public CommandDto convertToDto(Command command) {
        if(command==null){
            return null;
        }
        CommandDto dto = new CommandDto();
        BeanUtils.copyProperties(command, dto);
        return dto;
    }

    public List<CommandDto> convertToDtoList(List<Command> commandList) {
        if(CollectionUtils.isEmpty(commandList)){
            return Collections.emptyList();
        }
        List<CommandDto> dtoList = new ArrayList<>();
        commandList.forEach(command -> dtoList.add(convertToDto(command)));
        return dtoList;
    }
}
