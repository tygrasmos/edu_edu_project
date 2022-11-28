package ru.mos.tygras.eve.planned_assistance.model.eve.planetary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Link implements Serializable {

    @JsonProperty("destination_pin_id")
    private Long destinationPinId;
    @JsonProperty("link_level")
    private Integer linkLevel;
    @JsonProperty("source_pin_id")
    private Long sourcePinId;

    public Long getDestinationPinId() {
        return destinationPinId;
    }

    public void setDestinationPinId(Long destinationPinId) {
        this.destinationPinId = destinationPinId;
    }

    public Integer getLinkLevel() {
        return linkLevel;
    }

    public void setLinkLevel(Integer linkLevel) {
        this.linkLevel = linkLevel;
    }

    public Long getSourcePinId() {
        return sourcePinId;
    }

    public void setSourcePinId(Long sourcePinId) {
        this.sourcePinId = sourcePinId;
    }
}
