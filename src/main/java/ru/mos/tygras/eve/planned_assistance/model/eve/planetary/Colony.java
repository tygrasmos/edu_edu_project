package ru.mos.tygras.eve.planned_assistance.model.eve.planetary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Colony implements Serializable {

    @JsonProperty("last_update")
    private String LastUpdate;
    @JsonProperty("num_pins")
    private Integer pins;
    @JsonProperty("owner_id")
    private Integer ownerId;
    @JsonProperty("planet_id")
    private Integer planetId;
    @JsonProperty("planet_type")
    private String planetType;
    @JsonProperty("solar_system_id")
    private Integer solarSystemId;
    @JsonProperty("upgrade_level")
    private Integer upgradeLevel;


    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }

    public Integer getPins() {
        return pins;
    }

    public void setPins(Integer pins) {
        this.pins = pins;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getPlanetId() {
        return planetId;
    }

    public void setPlanetId(Integer planetId) {
        this.planetId = planetId;
    }

    public String getPlanetType() {
        return planetType;
    }

    public void setPlanetType(String planetType) {
        this.planetType = planetType;
    }

    public Integer getSolarSystemId() {
        return solarSystemId;
    }

    public void setSolarSystemId(Integer solarSystemId) {
        this.solarSystemId = solarSystemId;
    }

    public Integer getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(Integer upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }
}
