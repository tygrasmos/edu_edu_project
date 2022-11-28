package ru.mos.tygras.eve.planned_assistance.dto.mapper;

import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.dto.CharacterIndustryJobDto;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterIndustryJob;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterIndustryJobService;
import ru.mos.tygras.eve.planned_assistance.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterIndustryDtoMapper {

    private final CharacterIndustryJobService service;
    private final DateUtils dateUtils;

    public CharacterIndustryDtoMapper(CharacterIndustryJobService service,
                                      DateUtils dateUtils){
        this.service = service;
        this.dateUtils = dateUtils;
    }

    public CharacterIndustryJob dtoToEntity(CharacterIndustryJobDto dto){
        return service.findById(dto.getId());
    }

    public CharacterIndustryJobDto entityToDto(CharacterIndustryJob entity){
        CharacterIndustryJobDto dto = new CharacterIndustryJobDto();
        LocalDateTime startDate = entity.getStartDateTime();
        LocalDateTime endDate = entity.getEndDateTime();
        dto.setCharacter(entity.getCharacter());
        dto.setActivity(entity.getActivity());
        dto.setApproved(entity.getApproved());
        dto.setDuration(entity.getDuration());
        dto.setBlueprintType(entity.getBlueprintType());
        dto.setId(entity.getId());
        dto.setJobId(entity.getJobId());
        dto.setLocationId(entity.getLocationId());
        dto.setProjectName(entity.getProjectName());
        dto.setRuns(entity.getRuns());
        dto.setStatus(entity.getStatus());
        dto.setStationId(entity.getStationId());
        dto.setStartDateTime(startDate);
        dto.setEndDateTime(endDate);
        dto.setTimeLeft(dateUtils.getTimeLeft(endDate));
        dto.setHourTimeLeft(dateUtils.getHourValueTimeLeft(endDate));
        return dto;
    }

    public List<CharacterIndustryJob> dtoListToEntityList(List<CharacterIndustryJobDto> dtoList){
        return dtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public List<CharacterIndustryJobDto> entityListToDtoList(List<CharacterIndustryJob> entityList){
        return entityList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public static class EndDateJobComparator implements Comparator<CharacterIndustryJobDto> {

        @Override
        public int compare(CharacterIndustryJobDto o1, CharacterIndustryJobDto o2) {
            return o1.getEndDateTime().compareTo(o2.getEndDateTime());
        }
    }

    public static class CharacterComparator implements Comparator<CharacterIndustryJobDto> {

        @Override
        public int compare(CharacterIndustryJobDto o1, CharacterIndustryJobDto o2) {
            return o1.getCharacter().getCharacterName().compareTo(o2.getCharacter().getCharacterName());
        }
    }

    public static class ProjectComparator implements Comparator<CharacterIndustryJobDto> {

        @Override
        public int compare(CharacterIndustryJobDto o1, CharacterIndustryJobDto o2) {
            return o1.getProjectName().compareTo(o2.getProjectName());
        }
    }











}
