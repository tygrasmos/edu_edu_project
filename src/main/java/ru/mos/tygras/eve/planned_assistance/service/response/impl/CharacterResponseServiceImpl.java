package ru.mos.tygras.eve.planned_assistance.service.response.impl;

import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterToken;
import ru.mos.tygras.eve.planned_assistance.model.eve.industry.IndustryJob;
import ru.mos.tygras.eve.planned_assistance.service.response.CharacterResponseService;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterTokenService;
import ru.mos.tygras.eve.planned_assistance.service.response.IndustryResponseService;
import ru.mos.tygras.eve.planned_assistance.service.jwt.JwTokenService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CharacterResponseServiceImpl implements CharacterResponseService {

    private final JwTokenService jwTokenService;
    private final CharacterTokenService characterTokenService;
    private final IndustryResponseService industryResponseService;

    public CharacterResponseServiceImpl(JwTokenService jwTokenService,
                                        CharacterTokenService characterTokenService,
                                        IndustryResponseService industryResponseService){
        this.jwTokenService = jwTokenService;
        this.characterTokenService = characterTokenService;
        this.industryResponseService = industryResponseService;
    }

    @Override
    public void updateAllInfo(List<Character> characterList) {
        if(characterList.size() != 0) {
            characterList.forEach(ch -> {

                if(tokenIsExpired(ch)){
                    jwTokenService.refreshAccessToken(ch);
                }

                List<IndustryJob> industryJobList = industryResponseService.getIndustryJobListByCharacter(ch);
                industryResponseService.saveIndustryJobsResponse(industryJobList, ch);
            });
        }
    }

    @Override
    public String getUrlForAddNewCharacter() {
        return jwTokenService.getUrlForAuthCode();
    }

    /** Проверим истекло ли время действия токена */
    private Boolean tokenIsExpired(Character character){
        CharacterToken characterToken = characterTokenService.findByCharacter(character);
        long expiresInMinutes = characterToken.getExpiresIn()/60;
        LocalDateTime date = characterToken.getDateCreate();
        return LocalDateTime.now().compareTo(date.plusMinutes(expiresInMinutes)) > 0;
    }
}
