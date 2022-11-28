package ru.mos.tygras.eve.planned_assistance.dto;

import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.entity.EveIndustryActivityType;
import ru.mos.tygras.eve.planned_assistance.model.entity.EveType;

import java.time.LocalDateTime;

public class CharacterIndustryJobDto {

    private Long id;
    private Character character;
    private EveIndustryActivityType activity;
    private EveType blueprintType;
    private Long jobId;
    private Long duration;
    private String timeLeft;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Long locationId;
    private Long stationId;
    private Integer runs;
    private String status;
    private String projectName;
    private Integer approved;
    private Integer hourTimeLeft;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public EveIndustryActivityType getActivity() {
        return activity;
    }

    public void setActivity(EveIndustryActivityType activity) {
        this.activity = activity;
    }

    public EveType getBlueprintType() {
        return blueprintType;
    }

    public void setBlueprintType(EveType blueprintType) {
        this.blueprintType = blueprintType;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs = runs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Integer getHourTimeLeft() {
        return hourTimeLeft;
    }

    public void setHourTimeLeft(Integer hourTimeLeft) {
        this.hourTimeLeft = hourTimeLeft;
    }
}
