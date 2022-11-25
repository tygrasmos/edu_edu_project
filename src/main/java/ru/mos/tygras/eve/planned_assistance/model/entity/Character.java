package ru.mos.tygras.eve.planned_assistance.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "character")
@SequenceGenerator(name = "main_seq_gen", sequenceName = "MAIN_SEQUENCE", initialValue = 1000000, allocationSize = 1)
public class Character {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_seq_gen")
    private Long id;

    @Column(name = "character_id", nullable = false, unique = true)
    private Long characterId;

    @Column(name = "character_name", nullable = false, unique = true)
    private String characterName;

    @Column(name = "corporation_id", unique = true)
    private Long corporationId;

    @Column(name = "research_line_quantity", unique = true)
    private Integer researchLineQ;

    @Column(name = "production_line_quantity", unique = true)
    private Integer productLineQ;

    @Column(name = "reaction_line_quantity", unique = true)
    private Integer reactionLineQ;
}
