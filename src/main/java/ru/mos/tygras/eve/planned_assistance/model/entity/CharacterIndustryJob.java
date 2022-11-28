package ru.mos.tygras.eve.planned_assistance.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "character_industry")
@SequenceGenerator(name = "main_seq_gen", sequenceName = "MAIN_SEQUENCE", initialValue = 1000000, allocationSize = 1)
public class CharacterIndustryJob {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_seq_gen")
    private Long id;

    @ManyToOne(targetEntity = Character.class)
    @JoinColumn(name = "character_id")
    private Character character;

    @ManyToOne(targetEntity = EveIndustryActivityType.class)
    @JoinColumn(name = "activity_id")
    private EveIndustryActivityType activity;

    @ManyToOne(targetEntity = EveType.class)
    @JoinColumn(name = "blueprint_type_id")
    private EveType blueprintType;

    @Column(name = "job_id", nullable = false, unique = true)
    private Long jobId;

    @Column(name = "duration", nullable = false, unique = true)
    private Long duration;

    @Column(name = "start_date", nullable = false, unique = true)
    private LocalDateTime startDateTime;

    @Column(name = "end_date", nullable = false, unique = true)
    private LocalDateTime endDateTime;

    @Column(name = "output_location_id", nullable = false, unique = true)
    private Long locationId;

    @Column(name = "station_id", nullable = false, unique = true)
    private Long stationId;

    @Column(name = "runs", nullable = false, unique = true)
    private Integer runs;

    @Column(name = "status", nullable = false, unique = true)
    private String status;

    @Column(name = "project_name", unique = true)
    private String projectName;

    @Column(name = "is_approved", unique = true)
    private Integer approved;

}
