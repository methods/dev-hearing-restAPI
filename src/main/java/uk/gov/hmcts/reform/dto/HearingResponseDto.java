package uk.gov.hmcts.reform.dto;


import java.time.OffsetDateTime;
import java.util.UUID;

public record HearingResponseDto(

    UUID id,
    OffsetDateTime lastModified

) {

}
