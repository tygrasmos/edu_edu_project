package ru.mos.tygras.eve.planned_assistance.service.repository.impl;

import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterToken;
import ru.mos.tygras.eve.planned_assistance.repository.CharacterTokenRepository;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterTokenService;

import java.util.List;

@Service
public class CharacterTokenServiceImpl implements CharacterTokenService {

    private final CharacterTokenRepository characterTokenRepository;

    public CharacterTokenServiceImpl(CharacterTokenRepository characterTokenRepository){
        this.characterTokenRepository = characterTokenRepository;
    }

    @Override
    public CharacterToken save(CharacterToken characterToken) {
        characterTokenRepository.save(characterToken);
        return characterToken;
    }

    @Override
    public CharacterToken findById(Long characterTokenId) {
        return characterTokenRepository.findById(characterTokenId).get();
    }

    @Override
    public CharacterToken findByRefreshToken(String refreshToken) {
        return characterTokenRepository.findCharacterTokenByRefreshToken(refreshToken);
    }

    @Override
    public CharacterToken findByCharacter(Character character) {
        return characterTokenRepository.findCharacterTokenByCharacter(character);
    }

    @Override
    public List<CharacterToken> findAll() {
        return characterTokenRepository.findAll();
    }
}
