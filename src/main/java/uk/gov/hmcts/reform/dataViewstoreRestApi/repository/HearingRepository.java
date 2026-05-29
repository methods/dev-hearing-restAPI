package uk.gov.hmcts.reform.dataViewstoreRestApi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmcts.reform.dataViewstoreRestApi.entities.Hearing;

import java.util.List;
import java.util.UUID;

public interface HearingRepository extends JpaRepository<Hearing, UUID> {



}

