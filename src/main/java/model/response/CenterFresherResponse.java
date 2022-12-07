package model.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CenterFresherResponse (Long centerId, String centerName, Long fresherId,
        String fresherName, LocalDate startDate, LocalDate endDate){
    @Override
    public Long fresherId() {
        return fresherId;
    }

    @Override
    public Long centerId() {
        return centerId;
    }

    @Override
    public String centerName() {
        return centerName;
    }

    @Override
    public String fresherName() {
        return fresherName;
    }

    @Override
    public LocalDate startDate() {
        return startDate;
    }

    @Override
    public LocalDate endDate() {
        return endDate;
    }

}
