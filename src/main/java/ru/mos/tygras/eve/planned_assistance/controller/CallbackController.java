package ru.mos.tygras.eve.planned_assistance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mos.tygras.eve.planned_assistance.service.jwt.impl.JwTokenServiceImpl;

@Controller
public class CallbackController {

    private final JwTokenServiceImpl createTokens;

    public CallbackController(JwTokenServiceImpl createTokens){
        this.createTokens = createTokens;
    }

    @GetMapping(path = "/callback/")
    public String getSecretToken(@RequestParam("code") String code, @RequestParam("state") String state){
        createTokens.createAccessToken(code, state);
        return "redirect: /characters";
    }
}
