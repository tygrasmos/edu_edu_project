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
@Table(name = "character_token")
@SequenceGenerator(name = "main_seq_gen", sequenceName = "MAIN_SEQUENCE", initialValue = 1000000, allocationSize = 1)
public class CharacterToken {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_seq_gen")
    private Long id;

    @ManyToOne(targetEntity = Character.class)
    @JoinColumn(name = "character_id")
    private Character character;

    @Column(name = "access_token", nullable = false, unique = true)
    private String accessToken;

    @Column(name = "expires_in", nullable = false, unique = true)
    private Long expiresIn;

    @Column(name = "create_date", nullable = false, unique = true)
    private LocalDateTime dateCreate;

    @Column(name = "token_type", nullable = false, unique = true)
    private String tokenType;

    @Column(name = "refresh_token", nullable = false, unique = true)
    private String refreshToken;


}
