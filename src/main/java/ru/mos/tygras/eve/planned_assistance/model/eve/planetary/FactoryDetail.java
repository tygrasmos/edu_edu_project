package ru.mos.tygras.eve.planned_assistance.model.eve.planetary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class FactoryDetail implements Serializable {

    @JsonProperty("schematic_id")
    private Integer schematicId;

    public Integer getSchematicId() {
        return schematicId;
    }

    public void setSchematicId(Integer schematicId) {
        this.schematicId = schematicId;
    }
}
