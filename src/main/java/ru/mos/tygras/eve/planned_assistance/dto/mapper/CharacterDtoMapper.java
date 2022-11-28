package ru.mos.tygras.eve.planned_assistance.dto.mapper;

import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.dto.CharacterDto;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterDtoMapper {

    private final CharacterService service;

    public CharacterDtoMapper(CharacterService service){
        this.service = service;
    }


    public Character dtoToEntity(CharacterDto dto){
        return service.findById(dto.getId());
    }

    public CharacterDto entityToDto(Character entity){
        CharacterDto dto = new CharacterDto();
        dto.setId(entity.getId());
        dto.setCharacterId(entity.getCharacterId());
        dto.setCharacterName(entity.getCharacterName());
        dto.setCorporationId(entity.getCorporationId());
        return dto;
    }

    public List<Character> dtoListToEntityList(List<CharacterDto> dtoList){
        return dtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public List<CharacterDto> entityListToDtoList(List<Character> entityList){
        return entityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
