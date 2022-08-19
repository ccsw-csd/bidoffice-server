package com.ccsw.bidoffice.projecttype;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.offerdataproject.OfferDataProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.ccsw.bidoffice.projecttype.ProjectTypeRepository;
import com.ccsw.bidoffice.projecttype.ProjectTypeServiceImpl;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeEntity;

@ExtendWith(MockitoExtension.class)
public class ProjectTypeTest {

    public static final Integer TOTAL_OPPORTUNITY_TYPE = 1;
    private static final Long EXISTS_PROJECTTYPE_ID = 1L;
    private static final Long NOT_EXISTS_PROJECTTYPE_ID = 4L;

    @Mock
    private ProjectTypeRepository projectTypeRepository;

    @Mock
    private OfferDataProjectServiceImpl offerServiceImpl;

    @InjectMocks
    private ProjectTypeServiceImpl projectTypeServiceImpl;

    @Test
    public void shouldReturnListFileTypeOrderByPriority() {

        List<ProjectTypeEntity> list = new ArrayList<>();
        list.add(mock(ProjectTypeEntity.class));

        when(projectTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"))).thenReturn(list);

        List<ProjectTypeEntity> projectTypes = projectTypeServiceImpl.findAllProjectTypeOrderPriority();

        assertNotNull(projectTypes);
        assertEquals(TOTAL_OPPORTUNITY_TYPE, projectTypes.size());

    }

    @Test
    public void deleteExistsProjectTypeShouldDelete() throws AlreadyExistsException {

        when(this.offerServiceImpl.checkExistsByProjectTypeId(EXISTS_PROJECTTYPE_ID)).thenReturn(false);
        this.projectTypeServiceImpl.delete(EXISTS_PROJECTTYPE_ID);

        verify(this.projectTypeRepository).deleteById(EXISTS_PROJECTTYPE_ID);

    }

    @Test
    public void deleteIfNotExistsProjectTypeIdShouldThrowsException() throws AlreadyExistsException {

        when(this.offerServiceImpl.checkExistsByProjectTypeId(NOT_EXISTS_PROJECTTYPE_ID)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> projectTypeServiceImpl.delete(NOT_EXISTS_PROJECTTYPE_ID));

        verify(this.projectTypeRepository, never()).deleteById(NOT_EXISTS_PROJECTTYPE_ID);

    }
}
