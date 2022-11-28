package ru.mos.tygras.eve.planned_assistance.service;

import ru.mos.tygras.eve.planned_assistance.model.jwt.AccessTokenRequest;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AuthCodeRequest;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterToken;
import ru.mos.tygras.eve.planned_assistance.model.entity.RequestLog;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AuthorizedRequest;

public interface RequestCreatorService {

    AuthCodeRequest getAuthCodeRequestParam();

    AccessTokenRequest getAccessTokenRequestParam(RequestLog requestLog);

    AccessTokenRequest getAccessTokenByRefreshTokenRequestParam(CharacterToken charToken);

    AuthorizedRequest getAuthorizedRequestParam(CharacterToken charToken, String type);

    AuthorizedRequest getAuthorizedRequestParam(CharacterToken charToken, String type, String param);
}
