package ru.mos.tygras.eve.planned_assistance.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "eve_type")
@SequenceGenerator(name = "main_seq_gen", sequenceName = "MAIN_SEQUENCE", initialValue = 1000000, allocationSize = 1)
public class EveType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_seq_gen")
    private Long id;

    @Column(name = "type_id", nullable = false, unique = true)
    private Integer typeId;

    @Column(name = "group_type_id", nullable = false, unique = true)
    private Integer groupTypeId;

    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;
}
