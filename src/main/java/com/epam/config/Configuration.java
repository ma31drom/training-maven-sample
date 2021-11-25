package com.epam.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

    private static Properties props;

    public static Properties getApplicationProps() {
        if (props == null) {
            try (InputStream properties = Configuration.class.getClassLoader().getResourceAsStream("application.properties")) {
                props = new Properties();
                props.load(properties);
            } catch (IOException e) {
                LOGGER.error("Application initialisation failed", e);
                throw new IllegalArgumentException(e);
            }
        }
        return props;
    }


}
