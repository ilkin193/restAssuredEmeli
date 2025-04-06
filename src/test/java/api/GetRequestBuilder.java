package api;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetRequestBuilder {
    private final ObjectMapper mapper = new ObjectMapper();
    private final ObjectNode node = mapper.createObjectNode();

    public GetRequestBuilder withContext(String context) {
        node.put("context", context);
        return this;
    }

    public GetRequestBuilder withPage(Integer page) {
        node.put("page", page);
        return this;
    }

    public GetRequestBuilder withPerPage(Integer perPage) {
        node.put("per_page", perPage);
        return this;
    }

    public GetRequestBuilder withSearch(String search) {
        node.put("search", search);
        return this;
    }

    public GetRequestBuilder withAfter(String after) {
        if (after != null && after.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z")) {
            node.put("after", after);
        } else {
            throw new IllegalArgumentException("Дата должна быть в формате ISO8601: YYYY-MM-DDTHH:MM:SSZ");
        }
        return this;
    }

    public GetRequestBuilder withModifiedAfter(String modifiedAfter) {
        if (modifiedAfter != null && modifiedAfter.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z")) {
            node.put("modified_after", modifiedAfter);
        } else {
            throw new IllegalArgumentException("Дата должна быть в формате ISO8601: YYYY-MM-DDTHH:MM:SSZ");
        }
        return this;
    }

    public GetRequestBuilder withAuthors(List<Integer> authorIds) {
        if (authorIds != null && !authorIds.isEmpty()) {
            ArrayNode authorsArray = new ArrayNode(JsonNodeFactory.instance);
            for (Integer authorId : authorIds) {
                authorsArray.add(authorId);
            }
            node.set("author", authorsArray);
        } else {
            throw new IllegalArgumentException("Список авторов не должен быть пустым");
        }
        return this;
    }

    public GetRequestBuilder withAuthorExclude(List<Integer> authorExclude) {
        if (authorExclude != null && !authorExclude.isEmpty()) {
            ArrayNode arrayNode = node.putArray("author_exclude");
            for (Integer id : authorExclude) {
                arrayNode.add(id);
            }
        }
        return this;
    }

    public GetRequestBuilder withBefore(LocalDateTime beforeDate) {
        if (beforeDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            node.put("before", beforeDate.format(formatter));
        }
        return this;
    }

    public GetRequestBuilder withModifiedBefore(LocalDateTime modifiedBeforeDate) {
        if (modifiedBeforeDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            node.put("modified_before", modifiedBeforeDate.format(formatter));
        }
        return this;
    }

    public GetRequestBuilder withExclude(List<Integer> excludeIds) {
        if (excludeIds != null && !excludeIds.isEmpty()) {
            ArrayNode arrayNode = node.putArray("exclude");
            for (Integer id : excludeIds) {
                arrayNode.add(id);
            }
        }
        return this;
    }

    public GetRequestBuilder withInclude(List<Integer> includeIds) {
        if (includeIds != null && !includeIds.isEmpty()) {
            ArrayNode arrayNode = node.putArray("include");
            for (Integer id : includeIds) {
                arrayNode.add(id);
            }
        }
        return this;
    }

    public GetRequestBuilder withOffset(Integer offset) {
        if (offset != null && offset >= 0) {
            node.put("offset", offset);
        }
        return this;
    }

    public GetRequestBuilder withOrder(String order) {
        if (order != null && (order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc"))) {
            node.put("order", order.toLowerCase());
        }
        return this;
    }

    public GetRequestBuilder withOrderBy(List<String> orderByAttributes) {
        if (orderByAttributes != null && !orderByAttributes.isEmpty()) {
            ArrayNode arrayNode = node.putArray("orderby");
            for (String attribute : orderByAttributes) {
                if (isValidOrderByAttribute(attribute)) {
                    arrayNode.add(attribute.toLowerCase());
                }
            }
        }
        return this;
    }

    private boolean isValidOrderByAttribute(String attribute) {
        List<String> validAttributes = List.of("author", "date", "id", "include", "modified", "parent", "relevance", "slug", "include_slugs", "title");
        return validAttributes.contains(attribute.toLowerCase());
    }

    public GetRequestBuilder withSearchColumns(List<String> searchColumns) {
        if (searchColumns != null && !searchColumns.isEmpty()) {
            ArrayNode arrayNode = node.putArray("search_columns");
            for (String column : searchColumns) {
                arrayNode.add(column.toLowerCase());
            }
        }
        return this;
    }

    public GetRequestBuilder withSlug(List<String> slugs) {
        if (slugs != null && !slugs.isEmpty()) {
            ArrayNode arrayNode = node.putArray("slug");
            for (String slug : slugs) {
                arrayNode.add(slug);
            }
        }
        return this;
    }

    public GetRequestBuilder withStatus(List<String> statuses) {
        if (statuses != null && !statuses.isEmpty()) {
            ArrayNode arrayNode = node.putArray("status");
            for (String status : statuses) {
                arrayNode.add(status); // Добавляем статус в запрос
            }
        }
        return this;
    }

    public GetRequestBuilder withTaxRelation(String relation) {
        if (relation != null && (relation.equalsIgnoreCase("AND") || relation.equalsIgnoreCase("OR"))) {
            node.put("tax_relation", relation);
        }
        return this;
    }

    public GetRequestBuilder withCategories(String categories) {
        if (categories != null && !categories.isEmpty()) {
            node.put("categories", categories);
        }
        return this;
    }

    public GetRequestBuilder withCategoriesExclude(String categoriesExclude) {
        if (categoriesExclude != null && !categoriesExclude.isEmpty()) {
            node.put("categories_exclude", categoriesExclude);
        }
        return this;
    }

    public GetRequestBuilder withTags(String tags) {
        if (tags != null && !tags.isEmpty()) {
            node.put("tags", tags);
        }
        return this;
    }

    public GetRequestBuilder withTagsExclude(String tagsExclude) {
        if (tagsExclude != null && !tagsExclude.isEmpty()) {
            node.put("tags_exclude", tagsExclude);
        }
        return this;
    }

    public GetRequestBuilder withSticky(Boolean sticky) {
        if (sticky != null) {
            node.put("sticky", sticky);
        }
        return this;
    }

    public String build() {
        Iterator<Map.Entry<String, com.fasterxml.jackson.databind.JsonNode>> fields = node.fields();
        return "?" +
                new java.util.ArrayList<Map.Entry<String, com.fasterxml.jackson.databind.JsonNode>>() {{
                    fields.forEachRemaining(this::add);
                }}.stream()
                        .map(entry -> entry.getKey() + "=" + entry.getValue().asText())
                        .collect(Collectors.joining("&"));
    }
}
