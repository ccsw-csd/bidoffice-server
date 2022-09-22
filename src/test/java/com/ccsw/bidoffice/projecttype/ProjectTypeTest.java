package com.ccsw.bidoffice.projecttype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.offerdataproject.OfferDataProjectServiceImpl;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeDto;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeEntity;

@ExtendWith(MockitoExtension.class)
public class ProjectTypeTest {

    public static final Integer TOTAL_OPPORTUNITY_TYPE = 1;
    private static final Long EXISTS_PROJECTTYPE_ID = 1L;
    private static final String EXISTS_PROJECTTYPE_NAME = "Otros";
    private static final Integer EXISTS_PROJECTTYPE_PRIORITY = 1;
    private static final Long NOT_EXISTS_PROJECTTYPE_ID = 5L;
    private static final String NOT_EXISTS_PROJECTTYPE_NAME = "pepe";
    private static final Integer NOT_EXISTS_PROJECTTYPE_PRIORITY = 11;

    @Mock
    private ProjectTypeRepository projectTypeRepository;

    @Mock
    private OfferDataProjectServiceImpl offerServiceImpl;

    @InjectMocks
    private ProjectTypeServiceImpl projectTypeServiceImpl;

    private ProjectTypeDto projectTypeDto;
    private ProjectTypeEntity projectTypeEntityData;

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

    @Test
    public void modifyWithExistIdShouldModifyProjectType() throws AlreadyExistsException, EntityNotFoundException {
        this.projectTypeDto = new ProjectTypeDto();
        this.projectTypeDto.setId(EXISTS_PROJECTTYPE_ID);
        this.projectTypeDto.setName("");
        this.projectTypeDto.setPriority(10);
        this.projectTypeEntityData = new ProjectTypeEntity();
        this.projectTypeEntityData.setId(EXISTS_PROJECTTYPE_ID);
        this.projectTypeEntityData.setName("");
        this.projectTypeDto.setPriority(10);

        when(this.projectTypeRepository.findById(EXISTS_PROJECTTYPE_ID)).thenReturn(Optional.of(projectTypeEntityData));

        this.projectTypeServiceImpl.saveProjectType(projectTypeDto);

        verify(this.projectTypeRepository).save(projectTypeEntityData);
    }

    @Test
    public void modifyWithNotExistIdShouldThrowException() throws AlreadyExistsException, EntityNotFoundException {
        this.projectTypeDto = new ProjectTypeDto();
        this.projectTypeDto.setId(NOT_EXISTS_PROJECTTYPE_ID);
        ProjectTypeEntity projectTypeEntity = mock(ProjectTypeEntity.class);
        doReturn(Optional.empty()).when(this.projectTypeRepository).findById(NOT_EXISTS_PROJECTTYPE_ID);

        try {
            this.projectTypeServiceImpl.saveProjectType(projectTypeDto);
        } catch (EntityNotFoundException e) {
        }

        verify(this.projectTypeRepository, never()).save(projectTypeEntity);
    }

    @Test
    public void saveNewProjectTypeWhenPriorityAndNameDoesntExistsShouldSave()
            throws AlreadyExistsException, EntityNotFoundException {

        this.projectTypeDto = new ProjectTypeDto();
        this.projectTypeDto.setName(NOT_EXISTS_PROJECTTYPE_NAME);
        this.projectTypeDto.setPriority(NOT_EXISTS_PROJECTTYPE_PRIORITY);

        ArgumentCaptor<ProjectTypeEntity> projectTypeEntity = ArgumentCaptor.forClass(ProjectTypeEntity.class);

        this.projectTypeServiceImpl.saveProjectType(projectTypeDto);

        verify(this.projectTypeRepository).save(projectTypeEntity.capture());

        assertEquals(NOT_EXISTS_PROJECTTYPE_NAME, projectTypeEntity.getValue().getName());
        assertEquals(NOT_EXISTS_PROJECTTYPE_PRIORITY, projectTypeEntity.getValue().getPriority());
    }

    @Test
    public void saveNewProjectTypeWhenNameExistsShouldNotSave() throws AlreadyExistsException, EntityNotFoundException {

        this.projectTypeDto = new ProjectTypeDto();
        this.projectTypeDto.setName(EXISTS_PROJECTTYPE_NAME);
        this.projectTypeDto.setPriority(NOT_EXISTS_PROJECTTYPE_PRIORITY);

        ProjectTypeEntity projectTypeEntity = mock(ProjectTypeEntity.class);

        this.projectTypeServiceImpl.saveProjectType(projectTypeDto);

        when(this.projectTypeRepository.getByName(EXISTS_PROJECTTYPE_NAME)).thenReturn(projectTypeEntity);

        assertThrows(AlreadyExistsException.class, () -> projectTypeServiceImpl.saveProjectType(projectTypeDto));

        verify(this.projectTypeRepository, never()).save(projectTypeEntity);

    }

    @Test
    public void saveNewProjectTypeWhenPriorityExistsShouldNotSave()
            throws AlreadyExistsException, EntityNotFoundException {

        this.projectTypeDto = new ProjectTypeDto();
        this.projectTypeDto.setName(NOT_EXISTS_PROJECTTYPE_NAME);
        this.projectTypeDto.setPriority(EXISTS_PROJECTTYPE_PRIORITY);

        ProjectTypeEntity projectTypeEntity = mock(ProjectTypeEntity.class);

        this.projectTypeServiceImpl.saveProjectType(projectTypeDto);

        when(this.projectTypeRepository.getByPriority(EXISTS_PROJECTTYPE_PRIORITY)).thenReturn(projectTypeEntity);

        assertThrows(AlreadyExistsException.class, () -> projectTypeServiceImpl.saveProjectType(projectTypeDto));

        verify(this.projectTypeRepository, never()).save(projectTypeEntity);

    }

    @Test
    public void modifyProjectTypeWhenPriorityAndNameAlreadyExistsShoulNotSave() throws AlreadyExistsException {
        this.projectTypeDto = new ProjectTypeDto();
        this.projectTypeDto.setId(EXISTS_PROJECTTYPE_ID);
        this.projectTypeDto.setName(EXISTS_PROJECTTYPE_NAME);
        this.projectTypeDto.setPriority(EXISTS_PROJECTTYPE_PRIORITY);

        ProjectTypeEntity projectTypeEntity = mock(ProjectTypeEntity.class);

        lenient().when(this.projectTypeRepository.getByName(EXISTS_PROJECTTYPE_NAME)).thenReturn(projectTypeEntity);
        lenient().when(this.projectTypeRepository.getByPriority(EXISTS_PROJECTTYPE_PRIORITY))
                .thenReturn(projectTypeEntity);

        assertThrows(AlreadyExistsException.class, () -> projectTypeServiceImpl.saveProjectType(projectTypeDto));

        verify(this.projectTypeRepository, never()).save(projectTypeEntity);
    }

    @Test
    public void modifyProjectTypeWhenNameAndPriorityDoesntExistsShouldModify()
            throws AlreadyExistsException, EntityNotFoundException {

        this.projectTypeDto = new ProjectTypeDto();
        this.projectTypeDto.setId(EXISTS_PROJECTTYPE_ID);
        this.projectTypeDto.setName(NOT_EXISTS_PROJECTTYPE_NAME);
        this.projectTypeDto.setPriority(NOT_EXISTS_PROJECTTYPE_PRIORITY);

        ProjectTypeEntity projectTypeEntity = mock(ProjectTypeEntity.class);

        when(this.projectTypeRepository.getByName(NOT_EXISTS_PROJECTTYPE_NAME)).thenReturn(null);

        when(this.projectTypeRepository.getByPriority(NOT_EXISTS_PROJECTTYPE_PRIORITY)).thenReturn(null);

        when(this.projectTypeRepository.findById(EXISTS_PROJECTTYPE_ID)).thenReturn(Optional.of(projectTypeEntity));

        this.projectTypeServiceImpl.saveProjectType(projectTypeDto);

        verify(this.projectTypeRepository).save(projectTypeEntity);

    }
}
