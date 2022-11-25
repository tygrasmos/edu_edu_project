package ru.mos.tygras.eve.planned_assistance.model.jwt;

public class AuthCodeRequest {

    private String url;
    private String state;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
