package uk.gov.hmcts.reform.dataViewstoreRestApi.exception;

import java.util.UUID;

public class HearingNotFoundException extends RuntimeException {
    public HearingNotFoundException(UUID id) {
        super("Hearing not found: " + id);
    }
}
