package ru.mos.tygras.eve.planned_assistance.model.eve.planetary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Waypoint implements Serializable {

    @JsonProperty("waypoint")
    private Long waypoint;

    public Long getWaypoint() {
        return waypoint;
    }

    public void setWaypoint(Long waypoint) {
        this.waypoint = waypoint;
    }
}
