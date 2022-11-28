package ru.mos.tygras.eve.planned_assistance.model.eve.planetary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Pin implements Serializable {

    @JsonProperty("contents")
    private List<Content> contents;
    @JsonProperty("expiry_time")
    private String expiryTime;
    @JsonProperty("extractor_details")
    private ExtractorDetail extractorDetails;
    @JsonProperty("factory_details")
    private FactoryDetail factoryDetails;
    @JsonProperty("install_time")
    private String installTime;
    @JsonProperty("last_cycle_start")
    private String lastCycleStart;
    @JsonProperty("latitude")
    private Float latitude;
    @JsonProperty("longitude")
    private Float longitude;
    @JsonProperty("pin_id")
    private Long pinId;
    @JsonProperty("schematic_id")
    private Integer schematicId;
    @JsonProperty("type_id")
    private Integer typeId;

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public ExtractorDetail getExtractorDetails() {
        return extractorDetails;
    }

    public void setExtractorDetails(ExtractorDetail extractorDetails) {
        this.extractorDetails = extractorDetails;
    }

    public FactoryDetail getFactoryDetails() {
        return factoryDetails;
    }

    public void setFactoryDetails(FactoryDetail factoryDetails) {
        this.factoryDetails = factoryDetails;
    }

    public String getInstallTime() {
        return installTime;
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public String getLastCycleStart() {
        return lastCycleStart;
    }

    public void setLastCycleStart(String lastCycleStart) {
        this.lastCycleStart = lastCycleStart;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Long getPinId() {
        return pinId;
    }

    public void setPinId(Long pinId) {
        this.pinId = pinId;
    }

    public Integer getSchematicId() {
        return schematicId;
    }

    public void setSchematicId(Integer schematicId) {
        this.schematicId = schematicId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
