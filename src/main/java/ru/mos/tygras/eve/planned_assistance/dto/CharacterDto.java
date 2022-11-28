package ru.mos.tygras.eve.planned_assistance.dto;

import java.util.List;

public class CharacterDto {

    private Long id;
    private Long characterId;
    private String characterName;
    private Long corporationId;
    private List<CharacterIndustryJobDto> jobDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Long getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(Long corporationId) {
        this.corporationId = corporationId;
    }

    public List<CharacterIndustryJobDto> getJobDtoList() {
        return jobDtoList;
    }

    public void setJobDtoList(List<CharacterIndustryJobDto> jobDtoList) {
        this.jobDtoList = jobDtoList;
    }
}
