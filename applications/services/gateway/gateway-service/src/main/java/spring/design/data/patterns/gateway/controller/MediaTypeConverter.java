package spring.design.data.patterns.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Gregory Green
 */
@Component
public class MediaTypeConverter {
    private final String defaultContentType;

    public MediaTypeConverter(@Value("${gateway.fallback.default.mediaType:application/json}") String defaultContentType) {
        this.defaultContentType = defaultContentType;
    }


    public String toMimeType(Object contentType) {
        var  contentTypeText = String.valueOf(contentType);

        if(Objects.isNull(contentTypeText) || contentTypeText.isEmpty())
            return defaultContentType;

        return contentTypeText;
    }
}