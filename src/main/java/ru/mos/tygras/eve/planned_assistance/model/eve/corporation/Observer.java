package ru.mos.tygras.eve.planned_assistance.model.eve.corporation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Observer implements Serializable {

    @JsonProperty("last_update")
    private String lastUpdate;
    @JsonProperty("observer_id")
    private Integer observerId;
    @JsonProperty("observer_type")
    private String observerType;

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getObserverId() {
        return observerId;
    }

    public void setObserverId(Integer observerId) {
        this.observerId = observerId;
    }

    public String getObserverType() {
        return observerType;
    }

    public void setObserverType(String observerType) {
        this.observerType = observerType;
    }
}
