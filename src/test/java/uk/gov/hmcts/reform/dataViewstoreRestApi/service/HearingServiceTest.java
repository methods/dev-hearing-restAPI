package uk.gov.hmcts.reform.dataViewstoreRestApi.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.hmcts.reform.dataViewstoreRestApi.dto.HearingDetailedResponse;
import uk.gov.hmcts.reform.dataViewstoreRestApi.dto.HearingResponse;
import uk.gov.hmcts.reform.dataViewstoreRestApi.entities.Hearing;
import uk.gov.hmcts.reform.dataViewstoreRestApi.repository.HearingRepository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HearingServiceTest {

    @Mock
    private HearingRepository mockRepository;

    @InjectMocks
    private HearingService service;

    // ======================================================================
    // A utility method for creating test Hearing entity objects
    // ======================================================================
    private Hearing createHearing(UUID id, OffsetDateTime lastModified) {

        OffsetDateTime testDateTime = OffsetDateTime.parse("2024-01-01T10:00:00Z");
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 12);


        return new Hearing(
            id,
            UUID.randomUUID(),      // judiciaryId
            UUID.randomUUID(),      // hearingTypeId
            UUID.randomUUID(),      // courtId
            lastModified,           // lastModifiedTs
            UUID.randomUUID(),      // courtRoomId
            35,                     // estimatedMins
            "TestJurisdiction",     // jurisdictionType
            testDateTime,           // hearingAllocatedTs
            testDateTime,           // hearingInitiatedTs
            testDateTime,           // hearingResultsAddedTs
            testDateTime,           // lastUpdatedTs
            "English",              // hearingLanguage
            true,                   // boxHearing
            testDateTime,           // hearingListedTs
            startDate,              // weekCommenceStartDate
            endDate,                // weekCommenceEndDate
            12,                     // weekCommenceDuration
            false,                  // duplicateFlag
            false,                  // deletedFlag
            true                    // weekCommenceDateRemoved
        );
    }

    @Test
    void shouldReturnAllHearingsMappedToDtos() {

        UUID id = UUID.randomUUID();
        OffsetDateTime lastModified = OffsetDateTime.parse("2024-01-01T10:00:00Z");
        Hearing hearingEntity = createHearing(id, lastModified);
        when(mockRepository.findAll()).thenReturn(List.of(hearingEntity));

        List<HearingResponse> results = service.getAllHearings();

        assertThat(results).isNotNull();
        assertThat(results).hasSize(1);

        assertThat(results.get(0).id()).isEqualTo(id);
        assertThat(results.get(0).lastModified()).isNotNull();

    }

    @Test
    void shouldHandleAndMapMultipleHearingsSuccessfully() {
        // Arrange
        Hearing h1 = createHearing(UUID.randomUUID(), OffsetDateTime.now());
        Hearing h2 = createHearing(UUID.randomUUID(), OffsetDateTime.now());

        when(mockRepository.findAll()).thenReturn(List.of(h1, h2));

        // Act
        List<HearingResponse> results = service.getAllHearings();

        // Assert
        assertThat(results).hasSize(2);
        assertThat(results.get(0).id()).isEqualTo(h1.getId());
        assertThat(results.get(1).id()).isEqualTo(h2.getId());
    }

    // Sad path test
    @Test
    void shouldReturnEmptyListWhenNoHearingsFound() {

        when(mockRepository.findAll()).thenReturn(List.of());

        List<HearingResponse> results = service.getAllHearings();

        assertThat(results).isEmpty();
        assertThat(results).hasSize(0);
    }

    // null check test
    @Test
    void shouldMapHearingToDtoEvenWhenHearingTypeIsNull() {

        UUID id = UUID.randomUUID();
        OffsetDateTime lastModified = null;
        Hearing hearingEntity = createHearing(id, lastModified);

        when(mockRepository.findAll()).thenReturn(List.of(hearingEntity));

        List<HearingResponse> results = service.getAllHearings();

        assertThat(results).isNotEmpty();
        assertThat(results.get(0).id()).isEqualTo(id);
        assertThat(results.get(0).lastModified()).isNull();

    }

    // Tests for getById

    @Nested
    class GetById {

        @Test
        void shouldReturnHearingDetailedResponseWhenHearingExists() {
            // GIVEN a valid Hearing entity
            UUID id = UUID.randomUUID();
            OffsetDateTime lastModified = OffsetDateTime.parse("2024-01-01T10:00:00Z");
            Hearing hearing = createHearing(id, lastModified);
            // AND a mockRepository configured to return it when queried
            when(mockRepository.findById(id)).thenReturn(Optional.of(hearing));

            // WHEN requesting a hearing by id
            HearingDetailedResponse result = service.getHearingById(id);

            // THEN the result should exist
            assertThat(result).isNotNull();
            // AND the id should match the queried id
            assertThat(result.id()).isEqualTo(id);
            // AND the timestamp should match
            assertThat(result.lastModifiedTs()).isEqualTo(lastModified);

        }
    }


}
