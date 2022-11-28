package ru.mos.tygras.eve.planned_assistance.service.jwt.impl;

import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.entity.Character;
import ru.mos.tygras.eve.planned_assistance.service.RequestCreatorService;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AccessToken;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AccessTokenRequest;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AuthCodeRequest;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterToken;
import ru.mos.tygras.eve.planned_assistance.model.entity.RequestLog;
import ru.mos.tygras.eve.planned_assistance.service.repository.CharacterTokenService;
import ru.mos.tygras.eve.planned_assistance.service.jwt.JwTokenService;
import ru.mos.tygras.eve.planned_assistance.service.repository.RequestLogService;
import ru.mos.tygras.eve.planned_assistance.service.jwt.VerifyJWToken;
import ru.mos.tygras.eve.planned_assistance.service.RestTemplateService;

import java.time.LocalDateTime;

@Service
public class JwTokenServiceImpl implements JwTokenService {

    private final RequestCreatorService requestCreator;
    private final RestTemplateService restTemplateService;
    private final CharacterTokenService characterTokenService;
    private final RequestLogService requestLogService;
    private final VerifyJWToken verifyJWToken;

    public JwTokenServiceImpl(RequestCreatorService requestCreator,
                              RestTemplateService restTemplateService,
                              VerifyJWToken verifyJWToken,
                              CharacterTokenService characterTokenService,
                              RequestLogService requestLogService){
        this.requestCreator = requestCreator;
        this.restTemplateService = restTemplateService;
        this.characterTokenService = characterTokenService;
        this.requestLogService = requestLogService;
        this.verifyJWToken = verifyJWToken;
    }

    @Override
    public String getUrlForAuthCode(){

        AuthCodeRequest authCodeRequest = requestCreator.getAuthCodeRequestParam();
        RequestLog requestLog = new RequestLog();
        requestLog.setState(authCodeRequest.getState());
        requestLog.setDateRequest(LocalDateTime.now());
        requestLogService.save(requestLog);
        return authCodeRequest.getUrl();

    }

    @Override
    public void createAccessToken(String authCode, String state){
        RequestLog requestLog = requestLogService.findByState(state);

        if(requestLog != null){
            requestLog.setAuthCode(authCode);
            AccessTokenRequest accessTokenRequest = requestCreator.getAccessTokenRequestParam(requestLogService.save(requestLog));
            saveAccessToken(getRequestResult(accessTokenRequest));
        } else {
            throw new RuntimeException("Отправленныя соль state не соответствует возвращенной");
        }
    }

    @Override
    public void refreshAccessToken(Character character){
        CharacterToken characterToken = characterTokenService.findByCharacter(character);
        AccessTokenRequest accessTokenRequest = requestCreator.getAccessTokenByRefreshTokenRequestParam(characterToken);
        saveAccessToken(getRequestResult(accessTokenRequest));
    }

    // Получим JWT для персонажа
    private AccessToken getRequestResult(AccessTokenRequest accessTokenRequest){
        return restTemplateService.getAuthenticationJWToken(accessTokenRequest);
    }

    // Сохраним полученный токен в базу
    private void saveAccessToken(AccessToken accessToken){
        CharacterToken characterToken = characterTokenService.findByRefreshToken(accessToken.getRefreshToken());
        if(characterToken == null) {
            characterToken = new CharacterToken();
        }
        try {
            verifyJWToken.verify(characterToken, accessToken);
            characterToken.setDateCreate(LocalDateTime.now());
            characterToken.setTokenType(accessToken.getTokenType());
            characterToken.setAccessToken(accessToken.getAccessToken());
            characterToken.setRefreshToken(accessToken.getRefreshToken());
            characterToken.setExpiresIn(Long.valueOf(accessToken.getExpiresIn()));
            characterTokenService.save(characterToken);

        } catch (Exception e){
            throw new RuntimeException("".concat(e.getMessage()));
        }
    }

}
