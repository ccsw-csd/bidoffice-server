package com.ccsw.bidoffice.sector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.sector.model.SectorDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SectorIT extends BaseITAbstract {

    private static final String SERVICE_PATH = "/sector/";
    private static final Integer TOTAL_SECTOR = 4;
    private static final Long EXISTING_SECTOR_ID = 2L;
    private static final Long NOT_EXISTING_SECTOR_ID = 3L;

    ParameterizedTypeReference<List<SectorDto>> responseTypeSector = new ParameterizedTypeReference<List<SectorDto>>() {
    };

    /**
     * Recupera todos los sectores de la Base de Datos.
     * 
     * El test debe devolver 4 sectores.
     */
    @Test
    public void shouldReturnListSectorOrderByPriority() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<SectorDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseTypeSector);

        assertNotNull(response.getBody());
        assertEquals(TOTAL_SECTOR, response.getBody().size());
        assertTrue(response.getBody().stream().sorted(Comparator.comparing(SectorDto::getPriority))
                .collect(Collectors.toList()).equals(response.getBody()));
    }

    /**
     * Intenta borrar un sector que está siendo utilizado en alguna oferta.
     * 
     * El sector no debe poder ser borrado. Al intentar borrarlo, el ResponseEntity
     * debe devolver un estado HTTP 409 (conflicto).
     */
    @Test
    public void ifUsingSectorInOfferShouldNotDelete() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_SECTOR_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    /**
     * Intenta borrar un sector que NO está siendo utilizada en alguna oferta.
     * 
     * El sector debe ser borrada. Al borrarlo, el ResponseEntity debe devolver un
     * estado HTTP 200 (OK).
     */
    @Test
    public void ifNotUsingSectorInOfferShouldDelete() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NOT_EXISTING_SECTOR_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        ResponseEntity<List<SectorDto>> responseList = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeSector);

        assertNotNull(response);
        assertNotNull(responseList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(HttpStatus.OK, responseList.getStatusCode());
        assertEquals(3, responseList.getBody().size());
    }

    /**
     * Edita un sector existente si existe su identificador, siempre y cuando el
     * nombre y la prioridad no existan previamente.
     * 
     * Los asertos deben devolver que la inserción no es nula, que debe coincidir el
     * nombre a comprobar con el que se utiliza para editar el registro, que la
     * prioridad también debe coincidir con la utilizada para editar y el código
     * HTTP debe ser OK (200).
     */
    @Test
    public void ifNameAndPriorityAreOkShouldEdit() {

        SectorDto sectorDto = new SectorDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(sectorDto, getHeaders());

        sectorDto.setId(1L);
        sectorDto.setName("Zen++");
        sectorDto.setPriority(200);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        ResponseEntity<List<SectorDto>> responseAfter = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeSector);

        SectorDto editDto = responseAfter.getBody().stream().filter(element -> element.getName().equals("Zen++"))
                .findFirst().orElse(null);

        assertNotNull(editDto);
        assertEquals("Zen++", editDto.getName());
        assertEquals(200, editDto.getPriority());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Intenta editar un sector existente, pero se utiliza un nombre que ya existe
     * en la base de datos.
     * 
     * El test no debe modificar el registro y debe devolver un HttpStatus de
     * conflicto (409).
     */
    @Test
    public void ifNameExistsShouldNotEdit() {

        SectorDto sectorDto = new SectorDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(sectorDto, getHeaders());

        sectorDto.setId(1L);
        sectorDto.setName("Otros2");
        sectorDto.setPriority(200);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    /**
     * Edita un sector existente, modificando su nombre, siempre y cuando el nombre
     * no exista.
     * 
     * El test debe modificar el registro, debe comprobar que se ha modificado
     * correctamente verificando que coinciden nombre y prioridad y debe devolver un
     * estado HTTP OK (200).
     */
    @Test
    public void ifNameIsNotUsingShouldEdit() {

        SectorDto sectorDto = new SectorDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(sectorDto, getHeaders());

        sectorDto.setId(1L);
        sectorDto.setName("AnguriñaJS");
        sectorDto.setPriority(6);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<SectorDto>> responseAfter = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeSector);

        SectorDto editDto = responseAfter.getBody().stream().filter(element -> element.getName().equals("AnguriñaJS"))
                .findFirst().orElse(null);

        assertEquals("AnguriñaJS", editDto.getName());
        assertEquals(6, editDto.getPriority());
    }

    /**
     * Intenta editar un sector existente, pero se utiliza una prioridad ya aplicada
     * a otro sector.
     * 
     * El test no debe modificar el registro y debe devolver un estado HTTP Conflict
     * (409).
     */
    @Test
    public void ifPriorityExistsShouldNotEdit() {

        SectorDto sectorDto = new SectorDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(sectorDto, getHeaders());

        sectorDto.setId(1L);
        sectorDto.setName("Sushi++");
        sectorDto.setPriority(3);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    /**
     * Modifica la prioridad de un sector, siempre y cuando dicha prioridad no
     * exista en la base de datos.
     * 
     * El test debe modificar el sector y devolver un estado HTTP OK (200).
     */
    @Test
    public void ifPriorityIsNotUsingShouldEdit() {

        SectorDto sectorDto = new SectorDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(sectorDto, getHeaders());

        sectorDto.setId(1L);
        sectorDto.setName("Otros");
        sectorDto.setPriority(20);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<SectorDto>> responseAfter = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeSector);

        SectorDto editDto = responseAfter.getBody().stream().filter(element -> element.getName().equals("Otros"))
                .findFirst().orElse(null);

        assertEquals("Otros", editDto.getName());
        assertEquals(20, editDto.getPriority());

    }

    /**
     * Guarda un nuevo sector en la base de datos, siempre que no exista ni su
     * nombre, ni la prioridad.
     * 
     * El test debe guardar el registro, debe comprobar que se ha guardado
     * correctamente (coinciden nombre y prioridad con los utilizados en la
     * comparación) y debe devolver un estado HTTP OK (200).
     */
    @Test
    public void NewSectorWithNotExistingNameAndPriorityShouldCreate() {

        SectorDto sectorDto = new SectorDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(sectorDto, getHeaders());

        sectorDto.setName("Logistics");
        sectorDto.setPriority(5);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<SectorDto>> responseAfter = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeSector);

        SectorDto newsectorDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals("Logistics")).findFirst().orElse(null);

        assertEquals(5, responseAfter.getBody().size());
        assertEquals("Logistics", newsectorDto.getName());
        assertEquals(5, newsectorDto.getPriority());
    }

    /**
     * Intenta guardar un nuevo sector en la base de datos, utilizando un nombre ya
     * existente.
     * 
     * El test no debe guardar sector y debe devolver un estado HTTP CONFLICT (409).
     */
    @Test
    public void newTechnologyWithExistingNameShouldNotCreate() {

        SectorDto sectorDto = new SectorDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(sectorDto, getHeaders());

        sectorDto.setName("Otros");
        sectorDto.setPriority(4);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    /**
     * Intenta guardar un nuevo sector en la base de datos, utilizando una prioridad
     * ya existente.
     * 
     * El test no debe guardar el sector y debe devolver un estado HTTP CONFLICT
     * (409).
     */
    @Test
    public void NewTechnologyWithExistingPriorityShouldNotCreate() {

        SectorDto sectorDto = new SectorDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(sectorDto, getHeaders());

        sectorDto.setName("New Name");
        sectorDto.setPriority(1);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

}
