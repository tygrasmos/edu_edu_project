package ru.mos.tygras.eve.planned_assistance.model.eve.planetary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ColonyLayout implements Serializable {

    @JsonProperty("links")
    private List<Link> links;
    @JsonProperty("pins")
    private List<Pin> pins;
    @JsonProperty("routes")
    private List<Route> routes;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Pin> getPins() {
        return pins;
    }

    public void setPins(List<Pin> pins) {
        this.pins = pins;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
