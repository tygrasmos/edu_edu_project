package ru.mos.tygras.eve.planned_assistance.model.eve.planetary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable {

    @JsonProperty("content_type_id")
    private Integer contentTypeId;
    @JsonProperty("destination_pin_id")
    private Long destinationPinId;
    @JsonProperty("quantity")
    private Float quantity;
    @JsonProperty("route_id")
    private Long routeId;
    @JsonProperty("source_pin_id")
    private Long sourcePinId;
    @JsonProperty("waypoints")
    private List<Long> waypoints;// = new ArrayList<>();

    public Integer getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(Integer contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public Long getDestinationPinId() {
        return destinationPinId;
    }

    public void setDestinationPinId(Long destinationPinId) {
        this.destinationPinId = destinationPinId;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getSourcePinId() {
        return sourcePinId;
    }

    public void setSourcePinId(Long sourcePinId) {
        this.sourcePinId = sourcePinId;
    }

    public List<Long> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Long> waypoints) {
        this.waypoints = waypoints;
    }

   /* public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }*/
}
