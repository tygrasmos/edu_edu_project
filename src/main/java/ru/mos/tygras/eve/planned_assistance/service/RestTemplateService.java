package ru.mos.tygras.eve.planned_assistance.service;

import ru.mos.tygras.eve.planned_assistance.model.jwt.AccessToken;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AccessTokenRequest;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AuthorizedRequest;


public interface RestTemplateService {

    AccessToken getAuthenticationJWToken(AccessTokenRequest accessTokenRequest);

    String getResponseFromRestApi(AuthorizedRequest authoriseRequest);
}
