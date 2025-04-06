package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PostRequestBuilder {
    private final ObjectMapper mapper = new ObjectMapper();
    private final ObjectNode node = mapper.createObjectNode();

    public PostRequestBuilder withTitle(String title) {
        node.put("title", title);
        return this;
    }

    public PostRequestBuilder withContent(String content) {
        node.put("content", content);
        return this;
    }

    public PostRequestBuilder withStatus(String status) {
        node.put("status", status);
        return this;
    }

    public PostRequestBuilder withAuthor(int author) {
        node.put("author", author);
        return this;
    }

    public PostRequestBuilder withExcerpt(String excerpt) {
        node.put("excerpt", excerpt);
        return this;
    }

    public PostRequestBuilder withFeaturedMedia(int featuredMedia) {
        node.put("featured_media", featuredMedia);
        return this;
    }

    public PostRequestBuilder withCommentStatus(String commentStatus) {
        node.put("comment_status", commentStatus);
        return this;
    }

    public String build() {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (Exception e) {
            throw new RuntimeException("Failed to build JSON", e);
        }
    }
}