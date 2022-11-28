package ru.mos.tygras.eve.planned_assistance.model.jwt;

import java.util.HashMap;
import java.util.Map;

public class AuthorizedRequest {

    private String url;
    private Map<String, String> headers;

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
}
