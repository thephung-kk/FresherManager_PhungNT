package model.response;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record CenterResponse (Long centerId, String centerName, String centerCode,
                              LocalDate dob, String address){
    @Override
    public Long centerId() {
        return centerId;
    }

    @Override
    public String centerName() {
        return centerName;
    }

    @Override
    public String centerCode() {
        return centerCode;
    }

    @Override
    public LocalDate dob() {
        return dob;
    }

    @Override
    public String address() {
        return address;
    }
}
