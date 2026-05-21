package uk.gov.hmcts.reform.dto;

import java.util.List;

public record HearingCollectionResponse(

    List<HearingResponse> data
) {
}
