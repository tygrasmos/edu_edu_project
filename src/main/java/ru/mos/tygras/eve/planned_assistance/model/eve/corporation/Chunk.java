package ru.mos.tygras.eve.planned_assistance.model.eve.corporation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Chunk implements Serializable {

    @JsonProperty("chunk_arrival_time")
    private String chunkArrivalTime;
    @JsonProperty("extraction_start_time")
    private String extractionStartTime;
    @JsonProperty("moon_id")
    private Integer moonId;
    @JsonProperty("natural_decay_time")
    private String naturalDecayTime;
    @JsonProperty("structure_id")
    private Integer structureId;

    public String getChunkArrivalTime() {
        return chunkArrivalTime;
    }

    public void setChunkArrivalTime(String chunkArrivalTime) {
        this.chunkArrivalTime = chunkArrivalTime;
    }

    public String getExtractionStartTime() {
        return extractionStartTime;
    }

    public void setExtractionStartTime(String extractionStartTime) {
        this.extractionStartTime = extractionStartTime;
    }

    public Integer getMoonId() {
        return moonId;
    }

    public void setMoonId(Integer moonId) {
        this.moonId = moonId;
    }

    public String getNaturalDecayTime() {
        return naturalDecayTime;
    }

    public void setNaturalDecayTime(String naturalDecayTime) {
        this.naturalDecayTime = naturalDecayTime;
    }

    public Integer getStructureId() {
        return structureId;
    }

    public void setStructureId(Integer structureId) {
        this.structureId = structureId;
    }
}
