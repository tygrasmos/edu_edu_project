package ru.mos.tygras.eve.planned_assistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterToken;

public interface CharacterTokenRepository extends JpaRepository<CharacterToken, Long>, JpaSpecificationExecutor<CharacterToken> {

    CharacterToken findCharacterTokenByRefreshToken(String refreshToken);

    CharacterToken findCharacterTokenByCharacter(Character character);

}
