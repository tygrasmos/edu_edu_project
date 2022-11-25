package ru.mos.tygras.eve.planned_assistance.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AccessToken;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AccessTokenRequest;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AuthorizedRequest;
import ru.mos.tygras.eve.planned_assistance.service.RestTemplateService;
import ru.mos.tygras.eve.planned_assistance.service.prepare.SSLRestTemplateBuilder;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    private final SSLRestTemplateBuilder sslRestTemplateBuilder;

    public static final Logger log = LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    public RestTemplateServiceImpl(SSLRestTemplateBuilder sslRestTemplateBuilder){
        this.sslRestTemplateBuilder = sslRestTemplateBuilder;
    }

    @Override
    public AccessToken getAuthenticationJWToken(AccessTokenRequest accessTokenRequest){

        HttpHeaders headers = new HttpHeaders();
        accessTokenRequest.getHeaders().forEach(headers::add);
        HttpEntity<?> entity = new HttpEntity<>(accessTokenRequest.getRequestBody(), headers);

        log.info("trying get response by ".concat(accessTokenRequest.getUrl()));

        ResponseEntity<AccessToken> response = sslRestTemplateBuilder.getRestTemplate()
                .exchange(accessTokenRequest.getUrl(),
                        HttpMethod.POST,
                        entity,
                        AccessToken.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException(response.getStatusCode().toString());
        }
    }

    @Override
    public String getResponseFromRestApi(AuthorizedRequest authoriseRequest) {

        HttpHeaders headers = new HttpHeaders();
        authoriseRequest.getHeaders().forEach(headers::add);
        HttpEntity<?> entity = new HttpEntity<>(null, headers);

        log.info("trying get response by ".concat(authoriseRequest.getUrl()));

        ResponseEntity<String> response = sslRestTemplateBuilder.getRestTemplate()
                .exchange(authoriseRequest.getUrl(),
                        HttpMethod.GET,
                        entity,
                        String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException(response.getStatusCode().toString());
        }
    }

}
