package model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record FresherLanguageResponse(Long languageId,String languageName,Long fresherId,String fresherName) {
    @Override
    public Long languageId() {
        return languageId;
    }

    @Override
    public String languageName() {
        return languageName;
    }

    @Override
    public Long fresherId() {
        return fresherId;
    }

    @Override
    public String fresherName() {
        return fresherName;
    }
}
