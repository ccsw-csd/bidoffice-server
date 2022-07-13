package com.ccsw.bidoffice.opportunityType;

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
import org.springframework.data.domain.Sort;

import com.ccsw.bidoffice.opportunitytype.OpportunityTypeRepository;
import com.ccsw.bidoffice.opportunitytype.OpportunityTypeServiceImpl;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeEntity;

@ExtendWith(MockitoExtension.class)
public class OpportunityTypeTest {

    public static final Integer TOTAL_OPPORTUNITY_TYPE = 1;

    @Mock
    private OpportunityTypeRepository opportunityTypeRepository;

    @InjectMocks
    private OpportunityTypeServiceImpl opportunityTypeServiceImpl;

    @Test
    public void shouldReturnListFileTypeOrderByPriority() {

        List<OpportunityTypeEntity> list = new ArrayList<>();
        list.add(mock(OpportunityTypeEntity.class));

        when(opportunityTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(list);

        List<OpportunityTypeEntity> opportunityType = opportunityTypeServiceImpl.findAllOpportunityTypeOrderPriority();

        assertNotNull(opportunityType);
        assertEquals(TOTAL_OPPORTUNITY_TYPE, opportunityType.size());

    }
}
