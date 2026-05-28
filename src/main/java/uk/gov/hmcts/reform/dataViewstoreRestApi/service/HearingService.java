package uk.gov.hmcts.reform.dataViewstoreRestApi.service;


import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dataViewstoreRestApi.dto.HearingResponse;
import uk.gov.hmcts.reform.dataViewstoreRestApi.entities.Hearing;
import uk.gov.hmcts.reform.dataViewstoreRestApi.repository.HearingRepository;

import java.util.List;

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

    // Private Mapper helper
    private HearingResponse mapToDto( Hearing hearingEntity) {
        return new HearingResponse(
            hearingEntity.getId(),
            hearingEntity.getLastModifiedTs()
        );
    }

}
