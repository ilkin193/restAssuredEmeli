package api;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class BasePageObject {
    protected final RequestSpecification requestSpec;
    protected final Logger logger;

    public BasePageObject() {
        this.requestSpec = ApiConfig.getRequestSpec();
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    protected void assertExecutionTime(String operation, long executionTime) {
        ApiConfig.logExecutionTime(operation, executionTime);
        assertTrue(executionTime <= ApiConfig.MAX_EXECUTION_TIME_MS,
                String.format("%s request took too long: %d ms, expected less than %d ms",
                        operation, executionTime, ApiConfig.MAX_EXECUTION_TIME_MS));
    }

    protected Response executeRequest(String method, String endpoint, String body) {
        long startTime = System.currentTimeMillis();
        Response response;
        try {
            response = switch (method.toUpperCase()) {
                case "POST" -> given().spec(requestSpec).body(body).post(endpoint);
                case "PUT" -> given().spec(requestSpec).body(body).put(endpoint);
                case "DELETE" -> given().spec(requestSpec).delete(endpoint);
                case "GET" -> given().spec(requestSpec).get(endpoint);
                default -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
            };
        } catch (Exception e) {
            logger.error("Request failed: {} {}", method, endpoint, e);
            throw e;
        }
        long executionTime = System.currentTimeMillis() - startTime;
        assertExecutionTime(method + " " + endpoint, executionTime);
        return response;
    }
}