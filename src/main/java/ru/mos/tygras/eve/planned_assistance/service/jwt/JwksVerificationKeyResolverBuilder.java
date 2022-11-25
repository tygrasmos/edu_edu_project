package ru.mos.tygras.eve.planned_assistance.service.jwt;

import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class JwksVerificationKeyResolverBuilder {

    private JwtConsumer jwtConsumer;
    private final String jwksUrl;

    public JwksVerificationKeyResolverBuilder(@Value("${request.accessToken.jwks-url}") String jwksUrl){
        this.jwksUrl = jwksUrl;
    }

    @PostConstruct
    private void init(){
        HttpsJwks httpsJwks = new HttpsJwks(jwksUrl);
        HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJwks);
        this.jwtConsumer = new JwtConsumerBuilder()
                .setVerificationKeyResolver(httpsJwksKeyResolver)
                .setSkipDefaultAudienceValidation()
                .build();
    }

    public JwtConsumer getJwtConsumer(){
        return this.jwtConsumer;
    }

}
