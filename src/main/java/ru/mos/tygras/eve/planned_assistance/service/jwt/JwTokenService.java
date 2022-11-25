package ru.mos.tygras.eve.planned_assistance.service.jwt;

import ru.mos.tygras.eve.planned_assistance.model.entity.Character;

public interface JwTokenService {

    String getUrlForAuthCode();

    void createAccessToken(String authCode, String state);

    void refreshAccessToken(Character character);

}
