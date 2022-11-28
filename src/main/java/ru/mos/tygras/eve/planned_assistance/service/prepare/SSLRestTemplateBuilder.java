package ru.mos.tygras.eve.planned_assistance.service.prepare;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

@Service
public class SSLRestTemplateBuilder {

    private final String keyStorePass;
    private final String keyStore;

    public SSLRestTemplateBuilder(@Value("${secured.key-store}") String keyStore,
                                  @Value("${secured.key-store-password}") String keyStorePass){
        this.keyStore = keyStore;
        this.keyStorePass = keyStorePass;
    }

    public RestTemplate getRestTemplate() {

        try {
            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

            SSLContext sslContext = SSLContextBuilder.create()
                    .loadKeyMaterial(getKeyStore(keyStorePass)
                            , keyStorePass.toCharArray())
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();

            HttpClient client = HttpClients.custom().setSSLContext(sslContext).build();

            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(client);

            return new RestTemplate(requestFactory);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    private KeyStore getKeyStore (String password) throws Exception {
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new ClassPathResource(keyStore).getInputStream(), password.toCharArray());
        return ks;
    }
}
