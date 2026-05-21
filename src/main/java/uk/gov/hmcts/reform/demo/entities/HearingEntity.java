package uk.gov.hmcts.reform.demo.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "hearing", schema = "public")
@NoArgsConstructor()
public class HearingEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "judiciary_id", nullable = true)
    private UUID judiciaryId;

    @Column(name = "hearing_type_id", nullable = true)
    private UUID hearingTypeId;

    @Column(name = "court_id", nullable = true)
    private UUID courtId;

    @Column(name = "last_modified_ts", nullable = true)
    private OffsetDateTime lastModifiedTs;

    @Column(name = "court_room_id", nullable = true)
    private UUID courtRoomId;

    @Column(name = "estimated_mins", nullable = true)
    private Integer estimatedMins;

    @Column(name = "jurisdiction_type", nullable = true)
    private String jurisdictionType;

    @Column(name = "hearing_allocated_ts", nullable = true)
    private OffsetDateTime hearingAllocatedTs;

    @Column(name = "hearing_initiated_ts", nullable = true)
    private OffsetDateTime hearingInitiatedTs;

    @Column(name = "hearing_results_added_ts", nullable = true)
    private OffsetDateTime hearingResultsAddedTs;

    @Column(name = "last_updated_ts", nullable = true)
    private OffsetDateTime lastUpdatedTs;

    @Column(name = "hearing_language", nullable = true)
    private String hearingLanguage;

    @Column(name = "box_hearing", nullable = true)
    private Boolean boxHearing;

    @Column(name = "hearing_listed_ts", nullable = true)
    private OffsetDateTime hearingListedTs;

    @Column(name = "week_commence_start_date", nullable = true)
    private LocalDate weekCommenceStartDate;

    @Column(name = "week_commence_end_date", nullable = true)
    private LocalDate weekCommenceEndDate;

    @Column(name = "week_commence_duration", nullable = true)
    private Integer weekCommenceDuration;

    @Column(name = "duplicate_flag", nullable = true)
    private Boolean duplicateFlag;

    @Column(name = "deleted_flag", nullable = true)
    private Boolean deletedFlag;

    @Column(name = "week_commence_date_removed", nullable = true)
    private Boolean weekCommenceDateRemoved;
}




