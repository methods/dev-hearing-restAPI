package uk.gov.hmcts.reform.dataViewstoreRestApi.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dataViewstoreRestApi.dto.HearingDetailedResponse;
import uk.gov.hmcts.reform.dataViewstoreRestApi.dto.HearingResponse;
import uk.gov.hmcts.reform.dataViewstoreRestApi.entities.Hearing;
import uk.gov.hmcts.reform.dataViewstoreRestApi.exception.HearingNotFoundException;
import uk.gov.hmcts.reform.dataViewstoreRestApi.repository.HearingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HearingService {

    private final HearingRepository repository;

    public HearingService( HearingRepository repository) {
        this.repository = repository;
    }

    public List<HearingResponse> getAllHearings() {

        // call repo to get db entities
        List<Hearing> hearingEntities = repository.findAll();

        return hearingEntities.stream()
            .map(this::mapToDto)
            .toList();
    }

    public HearingDetailedResponse getHearingById( UUID id ) {

        return repository.findById(id)
            .map(this::toDto)
            .orElseThrow(() -> new HearingNotFoundException(id));
    }

    // Private Mapper helper
    private HearingResponse mapToDto( Hearing hearingEntity) {
        return new HearingResponse(
            hearingEntity.getId(),
            hearingEntity.getLastModifiedTs()
        );
    }

    private HearingDetailedResponse toDto ( Hearing hearing ) {
        return new HearingDetailedResponse(
            hearing.getId(),
            hearing.getJudiciaryId(),
            hearing.getHearingTypeId(),
            hearing.getCourtId(),
            hearing.getLastModifiedTs(),
            hearing.getCourtRoomId(),
            hearing.getEstimatedMins(),
            hearing.getJurisdictionType(),
            hearing.getHearingAllocatedTs(),
            hearing.getHearingInitiatedTs(),
            hearing.getHearingResultsAddedTs(),
            hearing.getLastUpdatedTs(),
            hearing.getHearingLanguage(),
            hearing.getBoxHearing(),
            hearing.getHearingListedTs(),
            hearing.getWeekCommenceStartDate(),
            hearing.getWeekCommenceEndDate(),
            hearing.getWeekCommenceDuration(),
            hearing.getDuplicateFlag(),
            hearing.getDeletedFlag(),
            hearing.getWeekCommenceDateRemoved()
        );
    }

}
