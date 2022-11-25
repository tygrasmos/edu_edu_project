package ru.mos.tygras.eve.planned_assistance.service.repository.impl;

import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.repository.CharacterRepository;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterService;
import ru.mos.tygras.eve.planned_assistance.service.security.PermissionService;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final PermissionService permissionService;

    public CharacterServiceImpl(CharacterRepository characterRepository,
                                PermissionService permissionService){
        this.characterRepository = characterRepository;
        this.permissionService = permissionService;
    }

    @Override
    public Character findByCharacterName(String characterName) {
        return characterRepository.findCharacterByCharacterName(characterName);
    }

    @Override
    public Character findByCharacterId(Long characterId) {
        return characterRepository.findCharacterByCharacterId(characterId);
    }

    @Override
    public Character findById(Long id) {
        return characterRepository.findById(id).get();
    }

    @Override
    public List<Character> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character save(Character character) {
        if(!isNameMatch(character.getCharacterName())){
            Character addedChar = characterRepository.save(character);
            permissionService.addPermissionForUser(addedChar);
            return addedChar;
        } else {
            throw new RuntimeException("Ошибка добавления нового персонажа. " +
                    "Персонаж с таким именем уже существует!");
        }
    }

    @Override
    public void delete(Character character) {
        characterRepository.delete(character);
    }

    private Boolean isNameMatch(String charName){
        Character character = characterRepository.findCharacterByCharacterName(charName);
        if(character != null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
