import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.extension.ExtendWith;

public class CreatePostPutDeleteRequestsTest {
    private static RequestSpecification requestSpec;

//    @BeforeAll
//    public static void setUp() {
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//        RestAssured.baseURI = "https://dev.emeli.in.ua/wp-json/wp/v2";
//        requestSpec = new RequestSpecBuilder()
//                .setContentType(ContentType.JSON)
//                .addHeader("Authorization", "Basic " + java.util.Base64.getEncoder()
//                        .encodeToString("admin:Engineer_123".getBytes()))
//                .build();
//    }
//
//
//    @Test
//    public void createPostWithValidCredentials() {
////        String requestBody1 = "{\n" +
////                "    \"title\": \"New Title\",\n" +
////                "    \"content\": \"New Content\"\n" +
////                "}";
//
//        String requestBody = """
//                {
//                    "title": "New Title",
//                    "content": "New Content"
//                }
//                """;
//
//        given()
//                .spec(requestSpec)
//                .body(requestBody)
//                .log().all()
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(201)
//                .body("title.raw", equalTo("New Title"))
//                .body("content.raw", equalTo("New Content"))
//                .log().all();
//    }
//
//    @Test
//    public void articleCanBePublishedImmediately() {
////        String requestBody = "{\n" +
////                "    \"title\": \"New Title\",\n" +
////                "    \"content\": \"New Content\",\n" +
////                "    \"status\": \"publish\"\n" +
////                "}";
//        String requestBody = """
//                {
//                    "title": "New Title",
//                    "content": "New Content",
//                    "status": "publish"
//                }
//                """;
//        given()
//                .spec(requestSpec)
//                .body(requestBody)
//                .log().all()
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(201)
//                .body("title.raw", equalTo("New Title"))
//                .body("content.raw", equalTo("New Content"))
//                .body("status", equalTo("publish"))
//                .log().all();
//    }
//
//    @Test
//    public void articleCanBePublishedImmediatelyWithAuthor() {
////        String requestBody = "{\n" +
////                "    \"title\": \"New Title\",\n" +
////                "    \"content\": \"New Content\",\n" +
////                "    \"status\": \"publish\",\n" +
////                "    \"author\": \"1\"\n" +
////                "}";
//        String requestBody = """
//                {
//                    "title": "New Title",
//                    "content": "New Content",
//                    "status": "publish",
//                    "author": "1"
//                }
//                """;
//        given()
//                .spec(requestSpec)
//                .body(requestBody)
//                .log().all()
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(201)
//                .body("title.raw", equalTo("New Title"))
//                .body("content.raw", equalTo("New Content"))
//                .body("status", equalTo("publish"))
//                .body("author", equalTo(1))
//                .log().all();
//    }
//
//    @Test
//    public void articleWithExperptField() {
////        String requestBody = "{\n" +
////                "    \"title\": \"New Title\",\n" +
////                "    \"content\": \"New Content\",\n" +
////                "    \"status\": \"publish\",\n" +
////                "    \"author\": \"1\",\n" +
////                "    \"excerpt\": \"Mamiyev\"\n" +
////                "}";
//        String requestBody = """
//                {
//                    "title": "New Title",
//                    "content": "New Content",
//                    "status": "publish",
//                    "author": "1",
//                    "experpt": ""
//                }
//                """;
//
//        given()
//                .spec(requestSpec)
//                .body(requestBody)
//                .log().all()
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(201)
//                .body("title.raw", equalTo("New Title"))
//                .body("content.raw", equalTo("New Content"))
//                .body("status", equalTo("publish"))
//                .body("author", equalTo(1))
//                .body("excerpt.raw", equalTo(""))
//                .log().all();
//    }
//
//    @Test
//    public void articleWithFeaturedMedia() {
////        String requestBody = "{\n" +
////                "    \"title\": \"New Title\",\n" +
////                "    \"content\": \"New Content\",\n" +
////                "    \"status\": \"publish\",\n" +
////                "    \"author\": \"1\",\n" +
////                "    \"excerpt\": \"Mamiyev\",\n" +
////                "    \"featured_media\": \"0\"\n" +
////                "}";
//        String requestBody = """
//                {
//                    "title": "New Title",
//                    "content": "New Content",
//                    "status": "publish",
//                    "author": "1",
//                    "experpt": "",
//                    "featured_media": "0"
//                }
//                """;
//
//        given()
//                .spec(requestSpec)
//                .body(requestBody)
//                .log().all()
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(201)
//                .body("title.raw", equalTo("New Title"))
//                .body("content.raw", equalTo("New Content"))
//                .body("status", equalTo("publish"))
//                .body("author", equalTo(1))
//                .body("excerpt.raw", equalTo(""))
//                .body("featured_media", equalTo(0))
//                .log().all();
//    }
//
//    @Test
//    public void articleWithAddingComments() {
////        String requestBody = "{\n" +
////                "    \"title\": \"New Title\",\n" +
////                "    \"content\": \"New Content\",\n" +
////                "    \"status\": \"publish\",\n" +
////                "    \"author\": \"1\",\n" +
////                "    \"excerpt\": \"Mamiyev\",\n" +
////                "    \"featured_media\": \"0\",\n" +
////                "    \"comment_status\": \"closed\"\n" +
////                "}";
//        String requestBody = """
//                {
//                    "title": "New Title",
//                    "content": "New Content",
//                    "status": "publish",
//                    "author": "1",
//                    "experpt": "",
//                    "featured_media": "0",
//                    "comment_status": "closed"
//                }
//                """;
//
//        Response createResponce =
//                given()
//                        .spec(requestSpec)
//                        .body(requestBody)
//                        .log().all()
//                        .when()
//                        .post("/posts")
//                        .then()
//                        .statusCode(201)
//                        .body("title.raw", equalTo("New Title"))
//                        .body("content.raw", equalTo("New Content"))
//                        .body("status", equalTo("publish"))
//                        .body("author", equalTo(1))
//                        .body("excerpt.raw", equalTo(""))
//                        .body("featured_media", equalTo(0))
//                        .body("comment_status", equalTo("closed"))
//                        .log().all()
//                        .extract().response();
//    }
//
//    @Test
//    public void createPostPutDeleteRequest() {
//
//        String requestBody = """
//                {
//                    "title": "New Title",
//                    "content": "New Content",
//                    "status": "publish",
//                    "author": "1",
//                    "excerpt": "",
//                    "featured_media": "0",
//                    "comment_status": "closed"
//                }
//                """;
//
//        long startTime = System.currentTimeMillis();
//
//        Response createResponce =
//                given()
//                        .spec(requestSpec)
//                        .body(requestBody)
//                        .log().all()
//                        .when()
//                        .post("/posts")
//                        .then()
//                        .statusCode(201)
//                        .extract().response();
//
//        long createTime = System.currentTimeMillis() - startTime;
//        System.out.println("Post article was created at this time: " + createTime + "ms");
//
//        int postId = createResponce.jsonPath().getInt("id");
//
//        String updateBody = """
//                {
//                    "title": "Updated Title",
//                    "content": "Updated Content"
//                }
//                """;
//
//        startTime = System.currentTimeMillis();
//
//
//        given()
//                .spec(requestSpec)
//                .body(updateBody)
//                .log().all()
//                .when()
//                .put("/posts/" + postId)
//                .then()
//                .statusCode(200)
//                .body("title.raw", equalTo("Updated Title"))
//                .body("content.raw", equalTo("Updated Content"))
//                .body("status", equalTo("publish"))
//                .body("author", equalTo(1))
//                .body("excerpt.raw", equalTo(""))
//                .body("featured_media", equalTo(0))
//                .body("comment_status", equalTo("closed"))
//                .log().all();
//        long updateTime = System.currentTimeMillis() - startTime;
//
//        given()
//                .spec(requestSpec)
//                .body(updateBody)
//                .log().all()
//                .when()
//                .delete("/posts/" + postId)
//                .then()
//                .statusCode(200)
//                .log().all();
//        long deleteTime = System.currentTimeMillis() - startTime;
//
//        System.out.println("Total time is: " + (createTime + updateTime + deleteTime) + "ms");
//    }

}

