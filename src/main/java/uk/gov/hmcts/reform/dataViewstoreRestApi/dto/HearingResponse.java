package uk.gov.hmcts.reform.dataViewstoreRestApi.dto;


import java.time.OffsetDateTime;
import java.util.UUID;

public record HearingResponse(

    UUID id,
    OffsetDateTime lastModified

) {

}
