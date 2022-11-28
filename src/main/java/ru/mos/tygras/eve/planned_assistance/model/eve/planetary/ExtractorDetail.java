package ru.mos.tygras.eve.planned_assistance.model.eve.planetary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ExtractorDetail implements Serializable{

    @JsonProperty("cycle_time")
    private Integer cycleTime;
    @JsonProperty("head_radius")
    private Float headRadius;
    @JsonProperty("heads")
    private List<Head> heads;
    @JsonProperty("product_type_id")
    private Integer productTypeId;
    @JsonProperty("qty_per_cycle")
    private Integer qtyPerCycle;

    public Integer getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(Integer cycleTime) {
        this.cycleTime = cycleTime;
    }

    public Float getHeadRadius() {
        return headRadius;
    }

    public void setHeadRadius(Float headRadius) {
        this.headRadius = headRadius;
    }

    public List<Head> getHeads() {
        return heads;
    }

    public void setHeads(List<Head> heads) {
        this.heads = heads;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getQtyPerCycle() {
        return qtyPerCycle;
    }

    public void setQtyPerCycle(Integer qtyPerCycle) {
        this.qtyPerCycle = qtyPerCycle;
    }
}
