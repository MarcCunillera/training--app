package com.rgb.training.app.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DynamicOdooConfig {

    // Ruta absoluta del fitxer de credencials
    private static final String CONFIG_PATH = "/home/marccunillera/Documentos/odoo-config.properties";

    public static Properties loadConfig() throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            props.load(fis);
        }
        return props;
    }

    public static String getOdooUrl() throws IOException {
        return loadConfig().getProperty("ODOO_URL");
    }

    public static String getDbName() throws IOException {
        return loadConfig().getProperty("ODOO_DB_NAME");
    }

    public static String getUserId() throws IOException {
        return loadConfig().getProperty("ODOO_USER_ID");
    }

    public static String getPassword() throws IOException {
        return loadConfig().getProperty("ODOO_PASSWORD");
    }
}

