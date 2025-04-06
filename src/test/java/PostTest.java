import api.PostPageObject;
import api.PostRequestBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Execution(ExecutionMode.CONCURRENT)
public class PostTest {
    private static PostPageObject postPageObject;

    @BeforeAll
    public static void setUp() {
        postPageObject = new PostPageObject();
    }

    @Test
    public void createPostPutDeleteRequest() {
        String title = "New Title!";
        String content = "New Content";

        List<Throwable> errors = new ArrayList<>();

        String createBody = new PostRequestBuilder()
                .withTitle(title)
                .withContent(content)
                .withStatus("publish")
                .withAuthor(1)
                .withExcerpt("")
                .withFeaturedMedia(0)
                .withCommentStatus("closed")
                .build();


        int postId = 15695;

        try {
            Response createResponse = postPageObject.createPost(createBody);
            postPageObject.verifyPostCreation(createResponse, title, content);
            postId = createResponse.jsonPath().getInt("id");
            System.out.println("POST Id is: " + postId);
        } catch (Throwable e) {
            errors.add(new AssertionError("Ошибка на этапе создания поста: " + e.getMessage(), e));
            System.out.println("POST Id is: " + postId);
        }

        try {
            String updateMyPost = new PostRequestBuilder()
                    .withTitle("Updated Title")
                    .withContent("Updated Content")
                    .build();

            postPageObject.updatePost(postId, updateMyPost);
            postPageObject.verifyPostUpdate(postId, "Updated Title", "Updated Content", "publish",
                    1, "", 0, "closed");
            System.out.println("Updated POST Id is: " + postId);
        } catch (Throwable e) {
            errors.add(new AssertionError("Ошибка на этапе обновления поста: " + e.getMessage(), e));
            System.out.println("Updated POST Id is: " + postId);
        }

        try {
            postPageObject.deletePost(postId);
        } catch (Throwable e) {
            errors.add(new AssertionError("Ошибка на этапе удаления поста: " + e.getMessage(), e));
        }


        if (!errors.isEmpty()) {
            AssertionError finalError = new AssertionError("Тест завершился с ошибками:");
            for (Throwable error : errors) {
                finalError.addSuppressed(error);
            }
            throw finalError;
        }
    }

    @Test
    public void getRequestForContext() {
        try {
            Response getContext = postPageObject.getForContext("view");
        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getRequestForPage() {
        try {
            Response getPage = postPageObject.getForPage(2);
        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getRequestForPerPage() {
        try {
            Response getPerPage = postPageObject.getForPerPage(2);
        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getRequestForSearch() {
        try {
            Response getForSearch = postPageObject.getForSearch("Something to search");
        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

//    @Test
//    public void getWithAfter() {
//        try {
//            Response getForAfter = postPageObject.getWithAfter("2025-03-28T00:00:00Z");
//        } catch (Throwable e) {
//            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
//        }
//    }

    @Test
    public void getWithModifiedAfter() {
        try {
            Response getForModifiedAfter = postPageObject.getWithModifiedAfter("2024-03-28T00:00:00Z");
        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getWithAuthors() {
        try {
            List<Integer> authors = List.of(1, 2);
            Response getWithAuthors = postPageObject.getWithAuthors(authors);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getWithAuthorExclude() {
        try {
            List<Integer> authorsExclude = List.of(1, 2);
            Response getWithAuthorExclude = postPageObject.getWithAuthorExclude(authorsExclude);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }


    @Test
    public void getWithBefore() {
        try {
            LocalDateTime beforeDate = LocalDateTime.now().minusDays(1);
            Response response = postPageObject.getWithBefore(beforeDate);
            response.then().statusCode(200);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getWithModifiedBefore() {
        try {
            LocalDateTime modifiedBeforeDate = LocalDateTime.now().minusDays(1);

            Response response = postPageObject.getWithModifiedBefore(modifiedBeforeDate);
            response.then().statusCode(200);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getWithExclude() {
        try {
            List<Integer> excludeIds = List.of(10, 20, 30);

            Response response = postPageObject.getWithExclude(excludeIds);
            response.then().statusCode(200);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getWithInclude() {
        try {
            List<Integer> includeIds = List.of(5, 15, 25);

            Response response = postPageObject.getWithInclude(includeIds);
            response.then().statusCode(200);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getWithOffset() {
        try {
            Integer offset = 10;

            Response response = postPageObject.getWithOffset(offset);
            response.then().statusCode(200);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getWithOrder() {
        try {
            String order = "desc";

            Response response = postPageObject.getWithOrder(order);
            response.then().statusCode(200);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

//    @Test
//    public void getWithOrderBy() {
//        try {
//            List<String> orderByAttributes = List.of("date", "author");
//
//            Response response = postPageObject.getWithOrderBy(orderByAttributes);
//            response.then().statusCode(200);
//
//        } catch (Throwable e) {
//            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
//        }
//    }

    @Test
    public void getWithSearchColumns() {
        try {
            List<String> searchColumns = List.of("title", "author");

            Response response = postPageObject.getWithSearchColumns(searchColumns);
            response.then().statusCode(200);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getWithSlug() {
        try {
            List<String> slugs = List.of("my-first-post", "First Post of Ilkin!");

            Response response = postPageObject.getWithSlug(slugs);
            response.then().statusCode(200);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getWithStatus() {
        try {
            List<String> statuses = List.of("published", "draft"); // Пример статусов

            Response response = postPageObject.getWithStatus(statuses);
            response.then().statusCode(200);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

    @Test
    public void getWithTaxRelation() {
        try {
            String relation = "AND";

            Response response = postPageObject.getWithTaxRelation(relation);
            response.then().statusCode(200);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }

//    @Test
//    public void getWithCategories() {
//        try {
//            String categories = "technology,education";
//
//            Response response = postPageObject.getWithCategories(categories);
//            response.then().statusCode(200);
//
//        } catch (Throwable e) {
//            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
//        }
//    }
//
//    @Test
//    public void getWithCategoriesExclude() {
//        try {
//            String categoriesExclude = "sports,health";
//
//            Response response = postPageObject.getWithCategoriesExclude(categoriesExclude);
//            response.then().statusCode(200);
//
//        } catch (Throwable e) {
//            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
//        }
//    }
//
//    @Test
//    public void getWithTags() {
//        try {
//            String tags = "java,programming";
//
//            Response response = postPageObject.getWithTags(tags);
//            response.then().statusCode(200);
//
//        } catch (Throwable e) {
//            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
//        }
//    }
//
//    @Test
//    public void getWithTagsExclude() {
//        try {
//            String tagsExclude = "javascript,python";
//
//            Response response = postPageObject.getWithTagsExclude(tagsExclude);
//            response.then().statusCode(200);
//
//        } catch (Throwable e) {
//            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
//        }
//    }

    @Test
    public void getWithSticky() {
        try {
            Boolean sticky = true;

            Response response = postPageObject.getWithSticky(sticky);
            response.then().statusCode(200);

        } catch (Throwable e) {
            throw new AssertionError("Тест завершился с ошибками: " + e.getMessage(), e);
        }
    }


}