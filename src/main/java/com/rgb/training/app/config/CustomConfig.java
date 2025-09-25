package com.rgb.training.app.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CustomConfig {

    public static String ODOO_URL;
    public static String ODOO_DB_NAME;
    public static String ODOO_USER_ID;
    public static String ODOO_PASSWORD;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        Properties props = new Properties();
        String configPath = "/home/marccunillera/Documentos/odoo-config.properties";
        
        try (FileInputStream fis = new FileInputStream(configPath)) {
            props.load(fis);
            ODOO_URL = props.getProperty("ODOO_URL");
            ODOO_DB_NAME = props.getProperty("ODOO_DB_NAME");
            ODOO_USER_ID = props.getProperty("ODOO_USER_ID");
            ODOO_PASSWORD = props.getProperty("ODOO_PASSWORD");

            System.out.println("[CONFIG] Odoo config carregada correctament:");
            System.out.println("URL: " + ODOO_URL);
            System.out.println("DB: " + ODOO_DB_NAME);
            System.out.println("USER_ID: " + ODOO_USER_ID);
            System.out.println("PASSWORD" + ODOO_PASSWORD );

        } catch (IOException e) {
            System.err.println("[CONFIG] Error carregant fitxer de configuració: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
