package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Statut;
import io.github.jhipster.application.repository.StatutRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link StatutResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class StatutResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDONNANCE = 1;
    private static final Integer UPDATED_ORDONNANCE = 2;
    private static final Integer SMALLER_ORDONNANCE = 1 - 1;

    @Autowired
    private StatutRepository statutRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restStatutMockMvc;

    private Statut statut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatutResource statutResource = new StatutResource(statutRepository);
        this.restStatutMockMvc = MockMvcBuilders.standaloneSetup(statutResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Statut createEntity(EntityManager em) {
        Statut statut = new Statut()
            .libelle(DEFAULT_LIBELLE)
            .ordonnance(DEFAULT_ORDONNANCE);
        return statut;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Statut createUpdatedEntity(EntityManager em) {
        Statut statut = new Statut()
            .libelle(UPDATED_LIBELLE)
            .ordonnance(UPDATED_ORDONNANCE);
        return statut;
    }

    @BeforeEach
    public void initTest() {
        statut = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatut() throws Exception {
        int databaseSizeBeforeCreate = statutRepository.findAll().size();

        // Create the Statut
        restStatutMockMvc.perform(post("/api/statuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statut)))
            .andExpect(status().isCreated());

        // Validate the Statut in the database
        List<Statut> statutList = statutRepository.findAll();
        assertThat(statutList).hasSize(databaseSizeBeforeCreate + 1);
        Statut testStatut = statutList.get(statutList.size() - 1);
        assertThat(testStatut.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testStatut.getOrdonnance()).isEqualTo(DEFAULT_ORDONNANCE);
    }

    @Test
    @Transactional
    public void createStatutWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statutRepository.findAll().size();

        // Create the Statut with an existing ID
        statut.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatutMockMvc.perform(post("/api/statuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statut)))
            .andExpect(status().isBadRequest());

        // Validate the Statut in the database
        List<Statut> statutList = statutRepository.findAll();
        assertThat(statutList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStatuts() throws Exception {
        // Initialize the database
        statutRepository.saveAndFlush(statut);

        // Get all the statutList
        restStatutMockMvc.perform(get("/api/statuts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statut.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].ordonnance").value(hasItem(DEFAULT_ORDONNANCE)));
    }
    
    @Test
    @Transactional
    public void getStatut() throws Exception {
        // Initialize the database
        statutRepository.saveAndFlush(statut);

        // Get the statut
        restStatutMockMvc.perform(get("/api/statuts/{id}", statut.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(statut.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.ordonnance").value(DEFAULT_ORDONNANCE));
    }

    @Test
    @Transactional
    public void getNonExistingStatut() throws Exception {
        // Get the statut
        restStatutMockMvc.perform(get("/api/statuts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatut() throws Exception {
        // Initialize the database
        statutRepository.saveAndFlush(statut);

        int databaseSizeBeforeUpdate = statutRepository.findAll().size();

        // Update the statut
        Statut updatedStatut = statutRepository.findById(statut.getId()).get();
        // Disconnect from session so that the updates on updatedStatut are not directly saved in db
        em.detach(updatedStatut);
        updatedStatut
            .libelle(UPDATED_LIBELLE)
            .ordonnance(UPDATED_ORDONNANCE);

        restStatutMockMvc.perform(put("/api/statuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStatut)))
            .andExpect(status().isOk());

        // Validate the Statut in the database
        List<Statut> statutList = statutRepository.findAll();
        assertThat(statutList).hasSize(databaseSizeBeforeUpdate);
        Statut testStatut = statutList.get(statutList.size() - 1);
        assertThat(testStatut.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testStatut.getOrdonnance()).isEqualTo(UPDATED_ORDONNANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingStatut() throws Exception {
        int databaseSizeBeforeUpdate = statutRepository.findAll().size();

        // Create the Statut

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatutMockMvc.perform(put("/api/statuts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statut)))
            .andExpect(status().isBadRequest());

        // Validate the Statut in the database
        List<Statut> statutList = statutRepository.findAll();
        assertThat(statutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatut() throws Exception {
        // Initialize the database
        statutRepository.saveAndFlush(statut);

        int databaseSizeBeforeDelete = statutRepository.findAll().size();

        // Delete the statut
        restStatutMockMvc.perform(delete("/api/statuts/{id}", statut.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Statut> statutList = statutRepository.findAll();
        assertThat(statutList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Statut.class);
        Statut statut1 = new Statut();
        statut1.setId(1L);
        Statut statut2 = new Statut();
        statut2.setId(statut1.getId());
        assertThat(statut1).isEqualTo(statut2);
        statut2.setId(2L);
        assertThat(statut1).isNotEqualTo(statut2);
        statut1.setId(null);
        assertThat(statut1).isNotEqualTo(statut2);
    }
}
