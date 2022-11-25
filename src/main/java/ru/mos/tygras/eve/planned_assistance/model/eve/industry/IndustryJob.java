package ru.mos.tygras.eve.planned_assistance.model.eve.industry;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class IndustryJob implements Serializable {

    @JsonProperty("activity_id")
    private Integer activityId;
    @JsonProperty("blueprint_id")
    private Long blueprintId;
    @JsonProperty("blueprint_location_id")
    private Long blueprintLocationId;
    @JsonProperty("blueprint_type_id")
    private Integer blueprintTypeId;
    @JsonProperty("completed_character_id")
    private Integer completedCharacterId;
    @JsonProperty("completed_date")
    private String completedDate;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("facility_id")
    private Long facilityId;
    @JsonProperty("installer_id")
    private Integer installerId;
    @JsonProperty("job_id")
    private Integer jobId;
    @JsonProperty("licensed_runs")
    private Integer licensedRuns;
    @JsonProperty("output_location_id")
    private Long outputLocationId;
    @JsonProperty("pause_date")
    private String pauseDate;
    @JsonProperty("probability")
    private Float probability;
    @JsonProperty("product_type_id")
    private Integer productTypeId;
    @JsonProperty("runs")
    private Integer runs;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("station_id")
    private Long stationId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("successful_runs")
    private Integer successfulRuns;


    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Long getBlueprintId() {
        return blueprintId;
    }

    public void setBlueprintId(Long blueprintId) {
        this.blueprintId = blueprintId;
    }

    public Long getBlueprintLocationId() {
        return blueprintLocationId;
    }

    public void setBlueprintLocationId(Long blueprintLocationId) {
        this.blueprintLocationId = blueprintLocationId;
    }

    public Integer getBlueprintTypeId() {
        return blueprintTypeId;
    }

    public void setBlueprintTypeId(Integer blueprintTypeId) {
        this.blueprintTypeId = blueprintTypeId;
    }

    public Integer getCompletedCharacterId() {
        return completedCharacterId;
    }

    public void setCompletedCharacterId(Integer completedCharacterId) {
        this.completedCharacterId = completedCharacterId;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public Integer getInstallerId() {
        return installerId;
    }

    public void setInstallerId(Integer installerId) {
        this.installerId = installerId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getLicensedRuns() {
        return licensedRuns;
    }

    public void setLicensedRuns(Integer licensedRuns) {
        this.licensedRuns = licensedRuns;
    }

    public Long getOutputLocationId() {
        return outputLocationId;
    }

    public void setOutputLocationId(Long outputLocationId) {
        this.outputLocationId = outputLocationId;
    }

    public String getPauseDate() {
        return pauseDate;
    }

    public void setPauseDate(String pauseDate) {
        this.pauseDate = pauseDate;
    }

    public Float getProbability() {
        return probability;
    }

    public void setProbability(Float probability) {
        this.probability = probability;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs = runs;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSuccessfulRuns() {
        return successfulRuns;
    }

    public void setSuccessfulRuns(Integer successfulRuns) {
        this.successfulRuns = successfulRuns;
    }
}
