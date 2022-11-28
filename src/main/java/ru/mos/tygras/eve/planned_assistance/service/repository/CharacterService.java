package ru.mos.tygras.eve.planned_assistance.service.repository;

import ru.mos.tygras.eve.planned_assistance.model.entity.Character;

import java.util.List;

public interface CharacterService {

    Character findByCharacterName(String characterName);

    Character findByCharacterId(Long characterId);

    Character findById(Long id);

    List<Character> findAll();

    Character save(Character character);

    void delete(Character character);

}
