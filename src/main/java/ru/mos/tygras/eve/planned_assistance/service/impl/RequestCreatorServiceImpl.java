package ru.mos.tygras.eve.planned_assistance.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AccessTokenRequest;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AuthCodeRequest;
import ru.mos.tygras.eve.planned_assistance.model.entity.CharacterToken;
import ru.mos.tygras.eve.planned_assistance.model.entity.RequestLog;
import ru.mos.tygras.eve.planned_assistance.model.jwt.AuthorizedRequest;
import ru.mos.tygras.eve.planned_assistance.service.RequestCreatorService;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.*;

@Service
public class RequestCreatorServiceImpl implements RequestCreatorService {

    /** Список областей доступа персонажа */
    private static List<String> scopes;
    /** Мапа названий запросов и путей */
    private static Map<String, String> nameRequest;
    /** Уникальный номер запроса */
    private static UUID state;

    private final String url;
    private final String authCodePath;
    private final String redirectUri;
    private final String clientId;
    private final String secretKey;
    private final String accessTokenPath;
    private final String authRequestUrl;
    private final String authRequestPath;

    private static final String RESPONSE_TYPE = "response_type";
    private static final String responseType = "code";
    private static final String GRANT_TYPE = "grant_type";
    private static final String GRANT_TYPE_CODE = "code";
    private static final String authorizationCode = "authorization_code";
    private static final String refreshCToken = "refresh_token";
    private static final String REDIRECTION_URI = "redirect_uri";
    private static final String CLIENT_ID = "client_id";
    private static final String SCOPE = "scope";
    private static final String STATE = "state";
    private static final String CODE_CHALLENGE = "code_challenge";

    public static final Logger log = LoggerFactory.getLogger(RequestCreatorServiceImpl.class);

    public RequestCreatorServiceImpl(@Value("${request.authCode.url}") String url,
                                     @Value("${request.authCode.path}") String authCodePath,
                                     @Value("${request.authCode.redirectUri}") String redirectUri,
                                     @Value("${secured.clientId}") String clientId,
                                     @Value("${secured.secretKey}") String secretKey,
                                     @Value("${request.accessToken.path}") String accessTokenPath,
                                     @Value("${request.authRequest.url}") String authRequestUrl,
                                     @Value("${request.authRequest.path}") String authRequestPath){
        this.url = url;
        this.authCodePath = authCodePath;
        this.redirectUri = redirectUri;
        this.clientId = clientId;
        this.secretKey = secretKey;
        this.accessTokenPath = accessTokenPath;
        this.authRequestUrl = authRequestUrl;
        this.authRequestPath = authRequestPath;
    }

    @PostConstruct
    public void init(){
        scopes = List.of("publicData esi-skills.read_skills.v1",
                "esi-skills.read_skillqueue.v1",
                "esi-wallet.read_character_wallet.v1",
                "esi-assets.read_assets.v1",
                "esi-planets.manage_planets.v1",
                "esi-characters.read_loyalty.v1",
                "esi-characters.read_agents_research.v1",
                "esi-industry.read_character_jobs.v1",
                "esi-markets.read_character_orders.v1",
                "esi-characters.read_blueprints.v1",
                "esi-location.read_online.v1",
                "esi-contracts.read_character_contracts.v1",
                "esi-industry.read_character_mining.v1",
                "esi-planets.read_customs_offices.v1");
        nameRequest = Map.of("industry", "/industry/jobs/",
                "planets", "/planets/",
                "assets", "/assets/",
                "character", "/",
                "corporation_extractions", "/mining/extractions/",
                "corporation_observers", "/mining/observers/");
    }

    /** Создание GET запроса для перенаправления пользователя на страницу авторизации
     * и получения кода авторизации для выбранного персонажа
     * params:
     *        RESPONSE_TYPE   - code
     *        REDIRECTION_URI - урл куда будет отправлен респонз
     *        CLIENT_ID       - код клиента (зарегистрированного приложения)
     *        SCOPE           - список областей доступа
     *        STATE           - сгенерированная соль
     *        */
    @Override
    public AuthCodeRequest getAuthCodeRequestParam(){
        AuthCodeRequest authCodeState = new AuthCodeRequest();
        log.info("Start create request from authenticate user.");
        authCodeState.setUrl(getUriFromGetAuthCode().toString());
        authCodeState.setState(getState().toString());
        log.info("Build request is complete. ");
        log.info("State : {}", authCodeState.getState());
        log.info("Request url : {}", authCodeState.getUrl());
        return authCodeState;
    }

    /** Создание POST запроса для получения токена доступа и рефреш токена
     * params:
     *        BODY:
     *           grant_type    - authorization_code
     *           code          - код авторизации полученный при первичном запросе
     *        HEADERS:
     *           Authorization - Base64 кодировка строки client_id:secret_key
     *                 (код клиента и секретный ключ зарегистрированного приложения)
     *           Content-Type  - application/x-www-form-urlencoded
     *           Host          - login.eveonline.com
     *           */
    @Override
    public AccessTokenRequest getAccessTokenRequestParam(RequestLog requestLog){

        AccessTokenRequest accessTokenRequest = new AccessTokenRequest();
        accessTokenRequest.setLog(requestLog);
        accessTokenRequest.setUrl(getUrlFromGetAccessToken().toString());
        accessTokenRequest.setRequestBody(createBody(requestLog.getAuthCode(), Boolean.FALSE));
        accessTokenRequest.setHeaders(createHeadersForAuthenticate());

        return accessTokenRequest;
    }

    /** Создание POST запроса для получения токена доступа по рефреш токену
     * params:
     *        BODY:
     *           grant_type    - refresh_token
     *           refresh_token - токен полученный ранее для обновления токена доступа
     *           scope         - необязательный параметр (scopes)
     *        HEADERS:
     *           Authorization - Base64 кодировка строки client_id:secret_key
     *                 (код клиента и секретный ключ зарегистрированного приложения)
     *           Content-Type  - application/x-www-form-urlencoded
     *           Host          - login.eveonline.com
     *           */
    @Override
    public AccessTokenRequest getAccessTokenByRefreshTokenRequestParam(CharacterToken charToken){

        AccessTokenRequest accessTokenState = new AccessTokenRequest();
        accessTokenState.setUrl(getUrlFromGetAccessToken().toString());
        accessTokenState.setRequestBody(createBody(charToken.getRefreshToken(), Boolean.TRUE));
        accessTokenState.setHeaders(createHeadersForAuthenticate());

        return accessTokenState;
    }

    /** Создание GET запроса для получения выбранного типа информации
     * о персонаже
     * params:
     *        HEADERS:
     *           Authorization - Bearer JWToken
     */
    @Override
    public AuthorizedRequest getAuthorizedRequestParam(CharacterToken charToken, String type) {
        AuthorizedRequest authoriseRequest = new AuthorizedRequest();
        authoriseRequest.setHeaders(createHeaderForAuthorizedRequest(charToken.getAccessToken()));
        authoriseRequest.setUrl(getUrlFromAuthorizedRequest(type, charToken.getCharacter().getCharacterId(), null).toString());
        return authoriseRequest;
    }

    /** Создание GET запроса для получения выбранного типа информации
     * о персонаже
     * params:
     *        HEADERS:
     *           Authorization - Bearer JWToken
     */
    @Override
    public AuthorizedRequest getAuthorizedRequestParam(CharacterToken charToken, String type, String param) {
        AuthorizedRequest authoriseRequest = new AuthorizedRequest();
        authoriseRequest.setHeaders(createHeaderForAuthorizedRequest(charToken.getAccessToken()));
        authoriseRequest.setUrl(getUrlFromAuthorizedRequest(type, charToken.getCharacter().getCharacterId(), param).toString());
        return authoriseRequest;
    }

    /** Получим путь для запроса кода авторизации */
    private URI getUriFromGetAuthCode(){
        createState();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        builder.path(authCodePath);
        builder.queryParam(RESPONSE_TYPE, responseType);
        builder.queryParam(REDIRECTION_URI, redirectUri);
        builder.queryParam(CLIENT_ID, clientId);
        builder.queryParam(SCOPE, getScopes());
        builder.queryParam(STATE, getState().toString());
        return builder.build().toUri();
    }

    /** Получим путь для запроса токена доступа */
    private URI getUrlFromGetAccessToken(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        builder.path(accessTokenPath);
        return builder.build().toUri();
    }

    /** Получим путь для авторизованного запроса по выбранному персонажу */
    private URI getUrlFromAuthorizedRequest(String type, Long characterId, String param){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(authRequestUrl);
        builder.path(createAuthorizedRequestPath(type, characterId, param));
        return builder.build().toUri();
    }

    /** Получим стандартный список скоупов которых хотим получить от персонажа */
    private String getScopes(){
        StringBuilder sb = new StringBuilder();
        scopes.forEach(s ->{
            sb.append(s);
            sb.append(" ");
        });
        return sb.substring(0, sb.length() - 1);
    }

    /** Создадим уникальный идентификатор запроса */
    private void createState(){
        state = UUID.randomUUID();
    }

    /** Создадим заголовки для запроса получения токена доступа */
    private Map<String, String> createHeadersForAuthenticate(){
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Basic " + createBase64Code());
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerMap.put("Host", "login.eveonline.com");
        return headerMap;
    }

    private Map<String, String> createHeaderForAuthorizedRequest(String accessJWToken){
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Bearer " + accessJWToken);
        return headerMap;
    }

    /** Создадим Body для POST запроса JWT */
    private String createBody(String code, boolean isRefresh){
        StringBuilder grantType = new StringBuilder();
        StringBuilder codeType = new StringBuilder();

        if(isRefresh){
            grantType.append(refreshCToken);
            codeType.append(refreshCToken);
        } else {
            grantType.append(authorizationCode);
            codeType.append(GRANT_TYPE_CODE);
        }

        String sb = GRANT_TYPE +
                "=" +
                grantType +
                "&" +
                codeType +
                "=" +
                code;
        return sb;
    }

    /** Создадим закодированный clientCode:clientSecret */
    private String createBase64Code(){
        String sb = clientId +
                ":" +
                secretKey;
        return Base64.getEncoder().encodeToString(sb.getBytes());
    }

    /** Создадим уникальный путь для получения информации о персонаже по типу запроса */
    private String createAuthorizedRequestPath(String type, Long characterId, String param){
        StringBuilder sb = new StringBuilder();
        sb.append(authRequestPath);
        sb.append("/characters/");
        sb.append(characterId);
        nameRequest.forEach((k, v) ->{
            if(k.equals(type)){
                sb.append(v);
            }
        });
        if(param != null){
            sb.append(param).append("/");
        }
        return sb.toString();
    }

    private UUID getState(){
        return state;
    }

}
