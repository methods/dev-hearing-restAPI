package uk.gov.hmcts.reform.dataViewstoreRestApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.dataViewstoreRestApi.dto.HearingResponse;
import uk.gov.hmcts.reform.dataViewstoreRestApi.service.HearingService;

import java.util.List;

@RestController
public class HearingController {

    private final HearingService hearingService;

    public HearingController( HearingService hearingService) {
        this.hearingService = hearingService;
    }

    @GetMapping("/hearings")
    public ResponseEntity<List<HearingResponse>> getHearings() {
        return ResponseEntity.ok(hearingService.getAllHearings());
    }


}
