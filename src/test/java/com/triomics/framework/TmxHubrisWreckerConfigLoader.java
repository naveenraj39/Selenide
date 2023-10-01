package com.triomics.framework;

import com.triomics.framework.common.AppConfigConstants;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.security.auth.login.Configuration;

public class TmxHubrisWreckerConfigLoader {


    public static Properties configurations = null;

    public void loadSelenideConfig() throws IOException {
        String envType = System.getProperty(AppConfigConstants.ENV_TYPE);
        File file = new ClassPathResource("selenide-" + envType + ".properties",
                this.getClass().getClassLoader()).getFile();
        
        configurations = new Properties();
        
        configurations.load(file.toURI().toURL().openStream());
        

        
   
    }}
