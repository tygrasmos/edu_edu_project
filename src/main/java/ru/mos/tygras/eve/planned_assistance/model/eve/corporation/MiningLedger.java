package ru.mos.tygras.eve.planned_assistance.model.eve.corporation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MiningLedger implements Serializable {

    @JsonProperty("character_id")
    private Integer characterId;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("recordedCorporationId")
    private Integer recorded_corporation_id;
    @JsonProperty("typeId")
    private Integer typeId;

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRecorded_corporation_id() {
        return recorded_corporation_id;
    }

    public void setRecorded_corporation_id(Integer recorded_corporation_id) {
        this.recorded_corporation_id = recorded_corporation_id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
