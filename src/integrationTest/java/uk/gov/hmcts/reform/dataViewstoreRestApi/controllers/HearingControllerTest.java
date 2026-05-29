package uk.gov.hmcts.reform.dataViewstoreRestApi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.hmcts.reform.dataViewstoreRestApi.dto.HearingResponse;
import uk.gov.hmcts.reform.dataViewstoreRestApi.service.HearingService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HearingController.class)
class HearingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HearingService service;


    @Test
    void shouldReturnOkAndAllHearingDtos() throws Exception {

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        OffsetDateTime lastModified1 = OffsetDateTime.parse("2024-01-01T10:00:00Z");
        OffsetDateTime lastModified2 = OffsetDateTime.parse("2024-06-01T10:00:00Z");

        List<HearingResponse> mockDtos = List.of(
            new HearingResponse(id1, lastModified1),
            new HearingResponse(id2, lastModified2)
        );

        when(service.getAllHearings()).thenReturn(mockDtos);

        mockMvc.perform(get("/hearings"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(id1.toString()))
            .andExpect(jsonPath("$[0].lastModified").exists())
            .andExpect(jsonPath("$[0].hearingTypeId").doesNotExist());

    }

    @Test
    void shouldReturnOkAndEmptyListWhenNoHearingsExist() throws Exception {
        when(service.getAllHearings()).thenReturn(List.of());

        mockMvc.perform(get("/hearings"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }
}
