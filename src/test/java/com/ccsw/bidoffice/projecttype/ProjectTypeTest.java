package com.ccsw.bidoffice.projecttype;

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

import com.ccsw.bidoffice.projecttype.ProjectTypeRepository;
import com.ccsw.bidoffice.projecttype.ProjectTypeServiceImpl;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeEntity;

@ExtendWith(MockitoExtension.class)
public class ProjectTypeTest {

    public static final Integer TOTAL_OPPORTUNITY_TYPE = 1;

    @Mock
    private ProjectTypeRepository projectTypeRepository;

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
}
