package com.triomics.framework.common.utils.spring.context.client.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.triomics.framework.common.AppConfigConstants;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.util.Map;

@Log4j2
@Component
public class SpringConfigClient {

    @Autowired
    private ObjectMapper om;

    @PostConstruct
    public void loadConfig() throws IOException {
        final String cfgSrvEp = System.getProperty(AppConfigConstants.ENV_CONFIGSERVER_ENDPOINT);
        final org.apache.logging.log4j.Logger log = LogManager.getLogger(SpringConfigClient.class);
        log.info("Loading config for {} environment", cfgSrvEp);
        //fetch config from config server
        //http://localhost:8888/master/admin-service-default.json
        final Map<String, String> configs = fetchConfig(cfgSrvEp);
    }

    private Map<String, String> fetchConfig(String cfgSrvEp) throws IOException {
        //hit config server
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(cfgSrvEp)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            TypeReference<Map<String, String>> typeRef = new TypeReference<Map<String, String>>() {
            };
            return om.readValue(string, typeRef);
        }
    }
}
