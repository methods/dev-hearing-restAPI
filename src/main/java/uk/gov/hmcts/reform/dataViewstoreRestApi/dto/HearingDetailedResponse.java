package uk.gov.hmcts.reform.dataViewstoreRestApi.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public record HearingDetailedResponse(

    UUID id,
    UUID judiciaryId,
    UUID hearingTypeId,
    UUID courtId,
    OffsetDateTime lastModifiedTs,
    UUID courtRoomId,
    Integer estimatedMins,
    String jurisdictionType,
    OffsetDateTime hearingAllocatedTs,
    OffsetDateTime hearingInitiatedTs,
    OffsetDateTime hearingResultsAddedTs,
    OffsetDateTime lastUpdatedTs,
    String hearingLanguage,
    Boolean boxHearing,
    OffsetDateTime hearingListedTs,
    LocalDate weekCommenceStartDate,
    LocalDate weekCommenceEndDate,
    Integer weekCommenceDuration,
    Boolean duplicateFlag,
    Boolean deletedFlag,
    Boolean weekCommenceDateRemoved

) {
}
