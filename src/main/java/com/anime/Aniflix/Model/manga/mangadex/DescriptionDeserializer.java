package com.anime.Aniflix.Model.manga.mangadex;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

/**
 * Handles both plain String and object-based descriptions like:
 * "description": "text"  OR  "description": { "en": "text" }
 */
public class DescriptionDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        if (node == null || node.isNull()) return "";

        if (node.isTextual()) {
            return node.asText();
        } else if (node.has("en")) {
            return node.get("en").asText();
        } else if (node.elements().hasNext()) {
            // fallback: return first language text if 'en' missing
            return node.elements().next().asText();
        }
        return "";
    }
}
