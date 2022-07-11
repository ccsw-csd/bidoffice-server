package com.ccsw.bidoffice.masterdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.ccsw.bidoffice.file_type.FileTypeRepository;
import com.ccsw.bidoffice.file_type.model.FileTypeEntity;
import com.ccsw.bidoffice.hyperscaler.HyperscalerRepository;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;
import com.ccsw.bidoffice.methodology.MethodologyRepository;
import com.ccsw.bidoffice.methodology.model.MethodologyEntity;
import com.ccsw.bidoffice.offer.OfferRepository;
import com.ccsw.bidoffice.offer.model.ClientsOnly;
import com.ccsw.bidoffice.offering.OfferingRepository;
import com.ccsw.bidoffice.offering.model.OfferingEntity;
import com.ccsw.bidoffice.opportunity_status.OpportunityStatusRepository;
import com.ccsw.bidoffice.opportunity_status.model.OpportunityStatusEntity;
import com.ccsw.bidoffice.opportunity_type.OpportunityTypeRepository;
import com.ccsw.bidoffice.opportunity_type.model.OpportunityTypeEntity;
import com.ccsw.bidoffice.person.PersonRepository;
import com.ccsw.bidoffice.person.model.PersonEntity;
import com.ccsw.bidoffice.person.model.PersonSearchDto;
import com.ccsw.bidoffice.project_type.ProjectTypeRepository;
import com.ccsw.bidoffice.project_type.model.ProjectTypeEntity;
import com.ccsw.bidoffice.sector.SectorRepository;
import com.ccsw.bidoffice.sector.model.SectorEntity;

@ExtendWith(MockitoExtension.class)
public class MasterDataTest {

    public static final Integer TOTAL_DATA = 1;

    public static final Integer EMPTY_DATA = 0;

    public static final String USERNAME_PERSON_ACTIVE = "aelmouss";

    public static final String USERNAME_PERSON_NOT_ACTIVE = "jopepe";

    public static final String CLIENT_CONTAINING = "user";

    public static final String CLIENT_NOT_CONTAINING = "admin";

    @Mock
    private SectorRepository sectorRepository;

    @Mock
    private OpportunityTypeRepository opportunityTypeRepository;

    @Mock
    private OpportunityStatusRepository opportunityStatusRepository;

    @Mock
    private FileTypeRepository fileTypeRepository;

    @Mock
    private HyperscalerRepository hyperscalerRepository;

    @Mock
    private MethodologyRepository methodologyRepository;

    @Mock
    private OfferingRepository offeringRepository;

    @Mock
    private ProjectTypeRepository projectTypeRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private MasterDataServiceImpl masterDataServiceImpl;

    private PersonSearchDto personSearchDto;

    private Integer sizeDataPriority;

    @BeforeEach
    public void setUp() {

        personSearchDto = new PersonSearchDto();
    }

    @Test
    public void findPageShouldReturnFilteredPerson() {

        personSearchDto.setUsername(USERNAME_PERSON_ACTIVE);

        List<PersonEntity> list = new ArrayList<>();
        list.add(mock(PersonEntity.class));

        when(this.personRepository.findAll(any(), eq(PageRequest.of(0, 15))))
                .thenReturn(new PageImpl<>(list, PageRequest.of(0, 15), list.size()));

        Page<PersonEntity> persons = this.masterDataServiceImpl.persons(personSearchDto);

        assertNotNull(persons.getContent());
        assertEquals(TOTAL_DATA, persons.getContent().size());

    }

    @Test
    public void findPageWithUsernameNotActiveShouldReturnEmptyPerson() {

        personSearchDto.setUsername(USERNAME_PERSON_NOT_ACTIVE);

        List<PersonEntity> list = new ArrayList<>();

        when(this.personRepository.findAll(any(), eq(PageRequest.of(0, 15))))
                .thenReturn(new PageImpl<>(list, PageRequest.of(0, 10), list.size()));

        Page<PersonEntity> persons = this.masterDataServiceImpl.persons(personSearchDto);

        assertNotNull(persons.getContent());
        assertEquals(EMPTY_DATA, persons.getContent().size());
    }

    @Test
    public void findClientShouldReturnAllListClients() {

        List<ClientsOnly> list = new ArrayList<>();
        list.add(mock(ClientsOnly.class));

        when(this.offerRepository.findFirst15DistinctByClientIgnoreCaseContaining(CLIENT_CONTAINING)).thenReturn(list);

        List<String> clients = this.masterDataServiceImpl.clients(CLIENT_CONTAINING);

        assertNotNull(clients);
        assertEquals(TOTAL_DATA, clients.size());
    }

    @Test
    public void findClientShouldReturnEmptyClients() {

        List<ClientsOnly> list = new ArrayList<>();

        when(this.offerRepository.findFirst15DistinctByClientIgnoreCaseContaining(CLIENT_NOT_CONTAINING))
                .thenReturn(list);

        List<String> clients = this.masterDataServiceImpl.clients(CLIENT_NOT_CONTAINING);

        assertNotNull(clients);
        assertEquals(EMPTY_DATA, clients.size());
    }

    @Nested
    @DisplayName("Order priority")
    public class OrderPriority {

        @AfterEach
        public void verify() {

            assertEquals(TOTAL_DATA, sizeDataPriority);
        }

        @Test
        public void shouldReturnListSectorOrderByPriority() {

            List<SectorEntity> list = new ArrayList<>();
            list.add(mock(SectorEntity.class));

            when(sectorRepository.findAllByOrderByPriorityAsc()).thenReturn(list);

            sizeDataPriority = sectorRepository.findAllByOrderByPriorityAsc().size();

        }

        @Test
        public void shouldReturnListOpportunityTypeOrderByPriority() {

            List<OpportunityTypeEntity> list = new ArrayList<>();
            list.add(mock(OpportunityTypeEntity.class));

            when(opportunityTypeRepository.findAllByOrderByPriorityAsc()).thenReturn(list);

            sizeDataPriority = opportunityTypeRepository.findAllByOrderByPriorityAsc().size();
        }

        @Test
        public void shouldReturnListOpportunityStatusOrderByPriority() {

            List<OpportunityStatusEntity> list = new ArrayList<>();
            list.add(mock(OpportunityStatusEntity.class));

            when(opportunityStatusRepository.findAllByOrderByPriorityAsc()).thenReturn(list);

            sizeDataPriority = opportunityStatusRepository.findAllByOrderByPriorityAsc().size();
        }

        @Test
        public void shouldReturnListFileTypeOrderByPriority() {

            List<FileTypeEntity> list = new ArrayList<>();
            list.add(mock(FileTypeEntity.class));

            when(fileTypeRepository.findAllByOrderByPriorityAsc()).thenReturn(list);

            sizeDataPriority = fileTypeRepository.findAllByOrderByPriorityAsc().size();

        }

        @Test
        public void shouldReturnListHyperscalerOrderByPriority() {

            List<HyperscalerEntity> list = new ArrayList<>();
            list.add(mock(HyperscalerEntity.class));

            when(hyperscalerRepository.findAllByOrderByPriorityAsc()).thenReturn(list);

            sizeDataPriority = hyperscalerRepository.findAllByOrderByPriorityAsc().size();
        }

        @Test
        public void shouldReturnListMethodologyOrderByPriority() {

            List<MethodologyEntity> list = new ArrayList<>();
            list.add(mock(MethodologyEntity.class));

            when(methodologyRepository.findAllByOrderByPriorityAsc()).thenReturn(list);

            sizeDataPriority = methodologyRepository.findAllByOrderByPriorityAsc().size();
        }

        @Test
        public void findPageShouldReturnListOfferingOrderByPriority() {

            List<OfferingEntity> list = new ArrayList<>();
            list.add(mock(OfferingEntity.class));

            when(offeringRepository.findAllByOrderByPriorityAsc()).thenReturn(list);

            sizeDataPriority = offeringRepository.findAllByOrderByPriorityAsc().size();

        }

        @Test
        public void shouldReturnListProjectTypesOrderByPriority() {

            List<ProjectTypeEntity> list = new ArrayList<>();
            list.add(mock(ProjectTypeEntity.class));

            when(projectTypeRepository.findAllByOrderByPriorityAsc()).thenReturn(list);

            sizeDataPriority = projectTypeRepository.findAllByOrderByPriorityAsc().size();
        }
    }

}
