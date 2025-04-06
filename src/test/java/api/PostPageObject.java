//package api;
//
//import io.restassured.response.Response;
//
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//
//import java.time.format.DateTimeFormatter;
//import java.time.LocalDateTime;
//
//public class PostPageObject extends BasePageObject {
//    private static final String POSTS_ENDPOINT = "/posts";
//
//    public Response createPost(String requestBody) {
//        Response response = executeRequest("POST", POSTS_ENDPOINT, requestBody);
//        response.then().statusCode(201);
//        return response;
//    }
//
//    public Response updatePostWithResponse(int postId, String requestBody) {
//        Response response = executeRequest("PUT", POSTS_ENDPOINT + "/" + postId, requestBody);
//        response.then().statusCode(200);
//        return response;
//    }
//
//    public void updatePost(int postId, String requestBody) {
//        updatePostWithResponse(postId, requestBody);
//    }
//
//    public void deletePost(int postId) {
//        Response response = executeRequest("DELETE", POSTS_ENDPOINT + "/" + postId, "");
//        response.then().statusCode(200);
//    }
//
//    public void verifyPostCreation(Response response, String title, String content) {
//        response.then()
//                .body("title.raw", equalTo(title))
//                .body("content.raw", equalTo(content));
//    }
//
//    public void verifyPostUpdate(int postId, String title, String content, String status, int author,
//                                 String excerpt, int featuredMedia, String commentStatus) {
//        Response getResponse = given()
//                .spec(requestSpec)
//                .when()
//                .get(POSTS_ENDPOINT + "/" + postId);
//        logger.info("GET Response after update: {}", getResponse.asPrettyString());
//        getResponse.then()
//                .statusCode(200)
//                .body("title.rendered", equalTo(title))
//                .body("content.rendered", equalTo("<p>Updated Content</p>\n"))
//                .body("status", equalTo(status))
//                .body("author", equalTo(author))
//                .body("excerpt.rendered", equalTo("<p>Updated Content</p>\n"))
//                .body("featured_media", equalTo(featuredMedia))
//                .body("comment_status", equalTo(commentStatus));
//    }
//
//    public Response getForContext(String context) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withContext(context)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when context param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getForPage(int page) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withPage(page)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when page param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getForPerPage(int perPage) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withPerPage(perPage)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when per page param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getForSearch(String someSearch) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withSearch(someSearch)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when search param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithAfter(String after) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withAfter(after)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when after param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithModifiedAfter(String modifiedAfter) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withModifiedAfter(modifiedAfter)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when modified after param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithAuthors(List<Integer> authorId) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withAuthors(authorId)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when author param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithAuthorExclude(List<Integer> authorId) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withAuthorExclude(authorId)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when author exclude param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithBefore(LocalDateTime beforeDate) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withBefore(beforeDate)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when before param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithModifiedBefore(LocalDateTime modifiedBeforeDate) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withModifiedBefore(modifiedBeforeDate)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when modified_before param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithExclude(List<Integer> excludeIds) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withExclude(excludeIds)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when exclude param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithInclude(List<Integer> includeIds) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withInclude(includeIds)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when include param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithOffset(Integer offset) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withOffset(offset)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when offset param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithOrder(String order) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withOrder(order)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when order param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithOrderBy(List<String> orderByAttributes) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withOrderBy(orderByAttributes)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when orderby param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithSearchColumns(List<String> searchColumns) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withSearchColumns(searchColumns)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when search_columns param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithSlug(List<String> slugs) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withSlug(slugs)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when slug param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithStatus(List<String> statuses) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withStatus(statuses)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when status param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithTaxRelation(String relation) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withTaxRelation(relation)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when tax_relation param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithCategories(String categories) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withCategories(categories)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when categories param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithCategoriesExclude(String categoriesExclude) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withCategoriesExclude(categoriesExclude)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when categories_exclude param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithTags(String tags) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withTags(tags)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when tags param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithTagsExclude(String tagsExclude) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withTagsExclude(tagsExclude)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when tags_exclude param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//    public Response getWithSticky(Boolean sticky) {
//        GetRequestBuilder requestBuilder = new GetRequestBuilder();
//        String queryParams = requestBuilder
//                .withSticky(sticky)
//                .build();
//
//        Response response = executeRequest("GET", POSTS_ENDPOINT + queryParams, "");
//        logger.info("GET Response when sticky param is specified: {}", response.asPrettyString());
//        response.then()
//                .statusCode(200);
//        return response;
//    }
//
//
//
//}
