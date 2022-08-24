package com.ccsw.bidoffice.opportunitystatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusEntity;

@ExtendWith(MockitoExtension.class)
public class OpportunityStatusTest {

    public static final Integer TOTAL_OPPORTUNITY_STATUS = 1;

    @Mock
    private OpportunityStatusRepository opportunityStatusRepository;

    @InjectMocks
    private OpportunityStatusServiceImpl opportunityStatusServiceImpl;

    @Test
    public void shouldReturnListFileTypeOrderByPriority() {

        List<OpportunityStatusEntity> list = new ArrayList<>();
        list.add(mock(OpportunityStatusEntity.class));

        when(opportunityStatusRepository.findAll()).thenReturn(list);

        List<OpportunityStatusEntity> opportunityStatus = opportunityStatusServiceImpl.findAllOpportunityStatus();

        assertNotNull(opportunityStatus);
        assertEquals(TOTAL_OPPORTUNITY_STATUS, opportunityStatus.size());

    }
}
