package ru.mos.tygras.eve.planned_assistance.model.jwt;

import ru.mos.tygras.eve.planned_assistance.model.entity.RequestLog;

import java.util.HashMap;
import java.util.Map;

public class AccessTokenRequest {

    private String url;
    private Map<String, String> headers;
    private String requestBody;

    private RequestLog log;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = new HashMap<>(headers);
    }

    public RequestLog getLog() {
        return log;
    }

    public void setLog(RequestLog log) {
        this.log = log;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

}
