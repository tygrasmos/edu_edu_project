package ru.mos.tygras.eve.planned_assistance.model.eve.planetary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Content implements Serializable {

    /** Количество материала на буфере*/
    @JsonProperty("amount")
    private Long amount;
    /** Тип материала */
    @JsonProperty("type_id")
    private Integer typeId;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
