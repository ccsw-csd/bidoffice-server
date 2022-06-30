package com.ccsw.bidoffice.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;

@ExtendWith(MockitoExtension.class)
public class OfferTest {

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferServiceImpl offerServiceImpl;

    private OfferSearchDto offerSearchDto;

    @BeforeEach
    public void setUp() {

        offerSearchDto = new OfferSearchDto();
    }

    @Test
    public void findPageShouldReturnUsersPage() {

        offerSearchDto.setPageable(PageRequest.of(0, 5));

        List<OfferEntity> offers = new ArrayList<>();
        offers.add(mock(OfferEntity.class));

        when(offerRepository.findAll(offerSearchDto.getPageable()))
                .thenReturn(new PageImpl<>(offers, offerSearchDto.getPageable(), offers.size()));

        Page<OfferEntity> page = offerServiceImpl.findPage(offerSearchDto);

        assertNotNull(page);
        assertEquals(offers.size(), page.getContent().size());

    }
}
