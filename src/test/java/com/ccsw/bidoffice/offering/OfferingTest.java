package com.ccsw.bidoffice.offering;

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

import com.ccsw.bidoffice.offering.model.OfferingEntity;

@ExtendWith(MockitoExtension.class)
public class OfferingTest {

    public static final Integer TOTAL_OFFERING = 1;

    @Mock
    private OfferingRepository offeringRepository;

    @InjectMocks
    private OfferingServiceImpl offeringServiceImpl;

    @Test
    public void shouldReturnListFileTypeOrderByPriority() {

        List<OfferingEntity> list = new ArrayList<>();
        list.add(mock(OfferingEntity.class));

        when(offeringRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(list);

        List<OfferingEntity> offerings = offeringServiceImpl.findAllOfferingOrderPriority();

        assertNotNull(offerings);
        assertEquals(TOTAL_OFFERING, offerings.size());

    }
}
