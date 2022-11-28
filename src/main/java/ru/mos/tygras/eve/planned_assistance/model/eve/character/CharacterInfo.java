package ru.mos.tygras.eve.planned_assistance.model.eve.character;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CharacterInfo implements Serializable {

    @JsonProperty("alliance_id")
    private Integer allianceId;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("bloodline_id")
    private Integer bloodlineId;
    @JsonProperty("corporation_id")
    private Integer corporationId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("faction_id")
    private Integer factionId;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("name")
    private String name;
    @JsonProperty("race_id")
    private Integer raceId;
    @JsonProperty("security_status")
    private Float securityStatus;
    @JsonProperty("title")
    private String title;

    public Integer getAllianceId() {
        return allianceId;
    }

    public void setAllianceId(Integer allianceId) {
        this.allianceId = allianceId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getBloodlineId() {
        return bloodlineId;
    }

    public void setBloodlineId(Integer bloodlineId) {
        this.bloodlineId = bloodlineId;
    }

    public Integer getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(Integer corporationId) {
        this.corporationId = corporationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFactionId() {
        return factionId;
    }

    public void setFactionId(Integer factionId) {
        this.factionId = factionId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRaceId() {
        return raceId;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }

    public Float getSecurityStatus() {
        return securityStatus;
    }

    public void setSecurityStatus(Float securityStatus) {
        this.securityStatus = securityStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
