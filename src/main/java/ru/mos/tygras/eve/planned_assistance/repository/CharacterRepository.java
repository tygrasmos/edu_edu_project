package ru.mos.tygras.eve.planned_assistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.access.prepost.PostFilter;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long>, JpaSpecificationExecutor<Character> {

    Character findCharacterByCharacterName(String characterName);

    Character findCharacterByCharacterId(Long characterId);

    @PostFilter("hasPermission(filterObject, 'ADMINISTRATION')")
    List<Character> findAll();
}
