package ru.mos.tygras.eve.planned_assistance.service.jwt;

import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtContext;
import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AccessToken;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterToken;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterService;

@Service
public class VerifyJWToken {

    private final static String scp = "scp";       // Список подписнных API (ArrayList)
    private final static String sub = "sub";       // Уникальный идентификатор персонажа в игре - (CHARACTER:EVE:123123123)
    private final static String iss = "iss";       // Указатель на сервер - (login.eveonline.com)
    private final static String exp = "exp";       // Long в милискундах до истечения срока годности токена
    private final static String aud = "aud";       // Указатель на eve - (EVE Online)
    private final static String name = "name";     // Имя персонажа - (xxx22ccc)
    private final static String jty = "jty";       // Какой то ключ??
    private final static String kid = "kid";       // Тип токена (JWT Signature Key)
    private final static String azp = "azp";       // Какой то ключ??
    private final static String tenant = "tenant"; // Идентификатор сервера (tranquility)
    private final static String tier = "tier";     // Доступность сервера (live)
    private final static String region = "region"; // Локация (world)
    private final static String owner = "owner";   // Какой то ключ??
    private final static String iat = "iat";       // Long в милискундах ???


    private final JwksVerificationKeyResolverBuilder keyResolverBuilder;
    private final CharacterService characterService;

    public VerifyJWToken(JwksVerificationKeyResolverBuilder keyResolverBuilder,
                         CharacterService characterService){
        this.keyResolverBuilder = keyResolverBuilder;
        this.characterService = characterService;
    }

    public void verify(CharacterToken characterToken, AccessToken accessToken){
        JwtConsumer consumer = keyResolverBuilder.getJwtConsumer();
        StringBuilder charName = new StringBuilder();
        StringBuilder charId = new StringBuilder();
        try {
            JwtContext context = consumer.process(accessToken.getAccessToken());
            context.getJwtClaims().getClaimsMap().forEach((k, v) ->{
                switch (k) {
                    case name:
                        charName.append((String) v);
                        break;
                    case sub:
                        charId.append(getCharacterId((String) v));
                        break;
                }
            });
            Character character = characterService.findByCharacterId(Long.valueOf(charId.toString()));
            if(character == null){
                character = createNewCharacter(charName.toString(), charId.toString());
            }
            characterToken.setCharacter(character);
        } catch (InvalidJwtException e){
            throw new RuntimeException("".concat(e.getMessage()));
        }
    }

    private Long getCharacterId(String str){
        String [] strArr = str.split(":");
        return Long.valueOf(strArr[2]);
    }

    private Character createNewCharacter(String charName, String charId){
        Character character = new Character();
        character.setCharacterId(Long.valueOf(charId));
        character.setCharacterName(charName);
        return characterService.save(character);
    }

}
