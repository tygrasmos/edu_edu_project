package ru.mos.tygras.eve.planned_assistance.service.repository;

import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterToken;

import java.util.List;

public interface CharacterTokenService {

    CharacterToken save(CharacterToken characterToken);

    CharacterToken findById(Long characterTokenId);

    CharacterToken findByRefreshToken(String refreshToken);

    CharacterToken findByCharacter(Character character);

    List<CharacterToken> findAll();
}
