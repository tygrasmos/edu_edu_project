package ru.mos.tygras.eve.planned_assistance.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "eve_industry_activity_type")
@SequenceGenerator(name = "main_seq_gen", sequenceName = "MAIN_SEQUENCE", initialValue = 1000000, allocationSize = 1)
public class EveIndustryActivityType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_seq_gen")
    private Long id;

    @Column(name = "activity_id", nullable = false, unique = true)
    private Integer activityId;

    @Column(name = "activity_name", nullable = false, unique = true)
    private String activityName;
}
