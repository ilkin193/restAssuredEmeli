package api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;


public class ApiConfig {
    private static final Logger logger = LoggerFactory.getLogger(ApiConfig.class);
    private static final Properties properties = loadProperties();
    public static final String BASE_URI = properties.getProperty("base.uri");
    public static final String USERNAME = properties.getProperty("username");
    public static final String PASSWORD = properties.getProperty("password");
    public static final long MAX_EXECUTION_TIME_MS = Long.parseLong(properties.getProperty("max.execution.time.ms"));

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (var input = ApiConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            }
            props.load(input);
            logger.info("Configuration loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration", e);
            throw new RuntimeException("Configuration loading failed", e);
        }
        return props;
    }

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setContentType(io.restassured.http.ContentType.JSON)
                .addHeader("Authorization", "Basic " + java.util.Base64.getEncoder()
                        .encodeToString((USERNAME + ":" + PASSWORD).getBytes()))
                .setBaseUri(BASE_URI)
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }

    public static void logExecutionTime(String operation, long time) {
        logger.info("{} time: {} ms", operation, time);
    }
}