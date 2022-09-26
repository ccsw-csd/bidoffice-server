package com.ccsw.bidoffice.technology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.technology.model.TechnologyDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TechnologyIT extends BaseITAbstract {

    private static final String SERVICE_PATH = "/technology/";
    private static final Integer TOTAL_TECHNOLOGIES = 3;
    private static final Long EXISTING_TECHNOLOGY_ID = 1L;
    private static final Long NOT_EXISTING_TECHNOLOGY_ID = 3L;

    private TechnologyDto technologyDto;

    ParameterizedTypeReference<List<TechnologyDto>> responseTypeTechnology = new ParameterizedTypeReference<List<TechnologyDto>>() {
    };

    /**
     * Antes de cada test, crea un objeto TechnologyDto en blanco.
     */
    @BeforeEach
    public void setUp() {
        this.technologyDto = new TechnologyDto();
    }

    /**
     * Recupera todas las tecnologías registradas en la base de datos.
     * 
     * Debe devolver tres tecnologías.
     */
    @Test
    public void findAllTechnologiesShouldReturnListOrderedByPriority() {

        HttpEntity<?> httpEntity = new HttpEntity<>(this.technologyDto, getHeaders());

        ResponseEntity<List<TechnologyDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeTechnology);

        assertNotNull(response);
        assertEquals(TOTAL_TECHNOLOGIES, response.getBody().size());
        assertTrue(response.getBody().stream().sorted(Comparator.comparing(TechnologyDto::getPriority))
                .collect(Collectors.toList()).equals(response.getBody()));
    }

    /**
     * Intenta borrar una tecnología que está siendo utilizada en alguna oferta.
     * 
     * La tecnología no debe poder ser borrada. Al intentar borrarla, el
     * ResponseEntity debe devolver un estado HTTP 409 (conflicto).
     */
    @Test
    public void ifUsingTechnologyInOfferShouldNotDelete() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_TECHNOLOGY_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    /**
     * Intenta borrar una tecnología que NO está siendo utilizada en alguna oferta.
     * 
     * La tecnología debe ser borrada. Al borrarla, el ResponseEntity debe devolver
     * un estado HTTP 200 (OK).
     */
    @Test
    public void IfNotUsingTechnologyInOfferShouldDelete() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NOT_EXISTING_TECHNOLOGY_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        ResponseEntity<List<TechnologyDto>> responseList = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeTechnology);

        assertNotNull(response);
        assertNotNull(responseList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(HttpStatus.OK, responseList.getStatusCode());
        assertEquals(2, responseList.getBody().size());
    }

    /**
     * Edita una tecnología existente si existe su identificador, siempre y cuando
     * el nombre y la prioridad no existan previamente.
     * 
     * Los asertos deben devolver que la inserción no es nula, que debe coincidir el
     * nombre a comprobar con el que se utiliza para editar el registro, que la
     * prioridad también debe coincidir con la utilizada para editar y el código
     * HTTP debe ser OK (200).
     */
    @Test
    public void ifNameAndPriorityAreOkShouldEdit() {

        TechnologyDto technologyDto = new TechnologyDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(technologyDto, getHeaders());

        technologyDto.setId(1L);
        technologyDto.setName("Zen++");
        technologyDto.setPriority(200);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        ResponseEntity<List<TechnologyDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeTechnology);

        TechnologyDto editDto = responseAfter.getBody().stream().filter(element -> element.getName().equals("Zen++"))
                .findFirst().orElse(null);

        assertNotNull(editDto);
        assertEquals("Zen++", editDto.getName());
        assertEquals(200, editDto.getPriority());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Intenta editar una tecnología existente, pero se utiliza un nombre que ya
     * existe en la base de datos.
     * 
     * El test no debe modificar el registro y debe devolver un HttpStatus de
     * conflicto (409).
     */
    @Test
    public void ifNameExistsShouldNotEdit() {

        TechnologyDto technologyDto = new TechnologyDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(technologyDto, getHeaders());

        technologyDto.setId(1L);
        technologyDto.setName("Quantum++");
        technologyDto.setPriority(200);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    /**
     * Edita una tecnología existente, modificando su nombre, siempre y cuando el
     * nombre no exista.
     * 
     * El test debe modificar el registro, debe comprobar que se ha modificado
     * correctamente verificando que coinciden nombre y prioridad y debe devolver un
     * estado HTTP OK (200).
     */
    @Test
    public void ifNameIsNotUsingShouldEdit() {

        TechnologyDto technologyDto = new TechnologyDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(technologyDto, getHeaders());

        technologyDto.setId(1L);
        technologyDto.setName("AnguriñaJS");
        technologyDto.setPriority(1);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<TechnologyDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeTechnology);

        TechnologyDto editDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals("AnguriñaJS")).findFirst().orElse(null);

        assertEquals("AnguriñaJS", editDto.getName());
        assertEquals(1, editDto.getPriority());
    }

    /**
     * Intenta editar una tecnología existente, pero se utiliza una prioridad ya
     * aplicada a otra tecnología.
     * 
     * El test no debe modificar el registro y debe devolver un estado HTTP Conflict
     * (409).
     */
    @Test
    public void ifPriorityExistsShouldNotEdit() {

        TechnologyDto technologyDto = new TechnologyDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(technologyDto, getHeaders());

        technologyDto.setId(1L);
        technologyDto.setName("Sushi++");
        technologyDto.setPriority(3);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    /**
     * Modifica la prioridad de una tecnología, siempre y cuando dicha prioridad no
     * exista en la base de datos.
     * 
     * El test debe modificar la tecnología y devolver un estado HTTP OK (200).
     */
    @Test
    public void ifPriorityIsNotUsingShouldEdit() {

        TechnologyDto technologyDto = new TechnologyDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(technologyDto, getHeaders());

        technologyDto.setId(1L);
        technologyDto.setName("admin");
        technologyDto.setPriority(20);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<TechnologyDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeTechnology);

        TechnologyDto editDto = responseAfter.getBody().stream().filter(element -> element.getName().equals("admin"))
                .findFirst().orElse(null);

        assertEquals("admin", editDto.getName());
        assertEquals(20, editDto.getPriority());

    }

    /**
     * Guarda una nueva tecnología en la base de datos, siempre que no exista ni su
     * nombre, ni la prioridad.
     * 
     * El test debe guardar el registro, debe comprobar que se ha guardado
     * correctamente (coinciden nombre y prioridad con los utilizados en la
     * comparación) y debe devolver un estado HTTP OK (200).
     */
    @Test
    public void NewTechnologyWithNotExistingNameAndPriorityShouldCreate() {
        TechnologyDto technologyDto = new TechnologyDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(technologyDto, getHeaders());

        technologyDto.setName("Prolog");
        technologyDto.setPriority(4);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<TechnologyDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeTechnology);

        TechnologyDto newtechnologyDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals("Prolog")).findFirst().orElse(null);

        assertEquals(4, responseAfter.getBody().size());
        assertEquals("Prolog", newtechnologyDto.getName());
        assertEquals(4, newtechnologyDto.getPriority());
    }

    /**
     * Intenta guardar una nueva tecnología en la base de datos, utilizando un
     * nombre ya existente.
     * 
     * El test no debe guardar la tecnología y debe devolver un estado HTTP CONFLICT
     * (409).
     */
    @Test
    public void newTechnologyWithExistingNameShouldNotCreate() {

        TechnologyDto technologyDto = new TechnologyDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(technologyDto, getHeaders());

        technologyDto.setName("admin");
        technologyDto.setPriority(4);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    /**
     * Intenta guardar una nueva tecnología en la base de datos, utilizando una
     * prioridad ya existente.
     * 
     * El test no debe guardar la tecnología y debe devolver un estado HTTP CONFLICT
     * (409).
     */
    @Test
    public void NewTechnologyWithExistingPriorityShouldNotCreate() {

        TechnologyDto technologyDto = new TechnologyDto();
        HttpEntity<?> httpEntity = new HttpEntity<>(technologyDto, getHeaders());

        technologyDto.setName("New Name");
        technologyDto.setPriority(1);

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

}
