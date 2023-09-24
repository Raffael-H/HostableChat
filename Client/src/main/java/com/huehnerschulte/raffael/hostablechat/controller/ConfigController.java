package com.huehnerschulte.raffael.hostablechat.controller;

import com.huehnerschulte.raffael.hostablechat.HostableChatApp;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigController {
    public Button confSaveButton;
    public Button confResetButton;
    public TextField confUrlInput;

    public void handleConfigSave(ActionEvent actionEvent) {
        createConfigFile(confUrlInput.getText());
    }

    public void handleConfigReset(ActionEvent actionEvent) {
        confUrlInput.setText(null);
    }

    public void updateData() {
        String configFilePath = null;
        try {
            configFilePath = getConfigFilePath();
        } catch (URISyntaxException e) {
            System.out.println("Path could not be found.");
        }
        if (configFilePath == null){
            return;
        }
        Map<String, String> configs = readConfigFile(new File(configFilePath));
        String url = configs.getOrDefault("url", "");
        confUrlInput.setText(url);
    }

    private void createConfigFile(String url) {
        try {
            String configFilePath = getConfigFilePath();
            File configFile = new File(configFilePath);
            // dummy Eintrag - Hier kann Logik/Methode hin um Config Inhalt zusammenzubauen.
            String configContent = "url="+url+"\n";
            // --------------------------------------------------------------------
            try (Writer writer = new FileWriter(configFile, false)) {
                writer.write(configContent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Konfigurationsdatei erstellt oder überschrieben: " + configFilePath);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getConfigFilePath() throws URISyntaxException {
        String jarPath = HostableChatApp.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        String jarDir = new File(jarPath).getParent();
        return jarDir + File.separator + "config.properties";
    }

    private static Map<String, String> readConfigFile(File configFile) {
        Properties properties = new Properties();
        Map<String, String> configMap = new HashMap<>();

        try (InputStream input = new FileInputStream(configFile)) {
            properties.load(input);

            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                if (value.equalsIgnoreCase("null")){
                    configMap.put(key, null);
                } else {
                    configMap.put(key, value);
                }
            }
        } catch (IOException e) {
            System.out.println("No config file found.");
        }

        return configMap;
    }
}
