package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.StatutDisponibilite;
import io.github.jhipster.application.repository.StatutDisponibiliteRepository;
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
 * Integration tests for the {@link StatutDisponibiliteResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class StatutDisponibiliteResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private StatutDisponibiliteRepository statutDisponibiliteRepository;

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

    private MockMvc restStatutDisponibiliteMockMvc;

    private StatutDisponibilite statutDisponibilite;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatutDisponibiliteResource statutDisponibiliteResource = new StatutDisponibiliteResource(statutDisponibiliteRepository);
        this.restStatutDisponibiliteMockMvc = MockMvcBuilders.standaloneSetup(statutDisponibiliteResource)
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
    public static StatutDisponibilite createEntity(EntityManager em) {
        StatutDisponibilite statutDisponibilite = new StatutDisponibilite()
            .libelle(DEFAULT_LIBELLE);
        return statutDisponibilite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StatutDisponibilite createUpdatedEntity(EntityManager em) {
        StatutDisponibilite statutDisponibilite = new StatutDisponibilite()
            .libelle(UPDATED_LIBELLE);
        return statutDisponibilite;
    }

    @BeforeEach
    public void initTest() {
        statutDisponibilite = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatutDisponibilite() throws Exception {
        int databaseSizeBeforeCreate = statutDisponibiliteRepository.findAll().size();

        // Create the StatutDisponibilite
        restStatutDisponibiliteMockMvc.perform(post("/api/statut-disponibilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statutDisponibilite)))
            .andExpect(status().isCreated());

        // Validate the StatutDisponibilite in the database
        List<StatutDisponibilite> statutDisponibiliteList = statutDisponibiliteRepository.findAll();
        assertThat(statutDisponibiliteList).hasSize(databaseSizeBeforeCreate + 1);
        StatutDisponibilite testStatutDisponibilite = statutDisponibiliteList.get(statutDisponibiliteList.size() - 1);
        assertThat(testStatutDisponibilite.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createStatutDisponibiliteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statutDisponibiliteRepository.findAll().size();

        // Create the StatutDisponibilite with an existing ID
        statutDisponibilite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatutDisponibiliteMockMvc.perform(post("/api/statut-disponibilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statutDisponibilite)))
            .andExpect(status().isBadRequest());

        // Validate the StatutDisponibilite in the database
        List<StatutDisponibilite> statutDisponibiliteList = statutDisponibiliteRepository.findAll();
        assertThat(statutDisponibiliteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStatutDisponibilites() throws Exception {
        // Initialize the database
        statutDisponibiliteRepository.saveAndFlush(statutDisponibilite);

        // Get all the statutDisponibiliteList
        restStatutDisponibiliteMockMvc.perform(get("/api/statut-disponibilites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statutDisponibilite.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getStatutDisponibilite() throws Exception {
        // Initialize the database
        statutDisponibiliteRepository.saveAndFlush(statutDisponibilite);

        // Get the statutDisponibilite
        restStatutDisponibiliteMockMvc.perform(get("/api/statut-disponibilites/{id}", statutDisponibilite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(statutDisponibilite.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStatutDisponibilite() throws Exception {
        // Get the statutDisponibilite
        restStatutDisponibiliteMockMvc.perform(get("/api/statut-disponibilites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatutDisponibilite() throws Exception {
        // Initialize the database
        statutDisponibiliteRepository.saveAndFlush(statutDisponibilite);

        int databaseSizeBeforeUpdate = statutDisponibiliteRepository.findAll().size();

        // Update the statutDisponibilite
        StatutDisponibilite updatedStatutDisponibilite = statutDisponibiliteRepository.findById(statutDisponibilite.getId()).get();
        // Disconnect from session so that the updates on updatedStatutDisponibilite are not directly saved in db
        em.detach(updatedStatutDisponibilite);
        updatedStatutDisponibilite
            .libelle(UPDATED_LIBELLE);

        restStatutDisponibiliteMockMvc.perform(put("/api/statut-disponibilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStatutDisponibilite)))
            .andExpect(status().isOk());

        // Validate the StatutDisponibilite in the database
        List<StatutDisponibilite> statutDisponibiliteList = statutDisponibiliteRepository.findAll();
        assertThat(statutDisponibiliteList).hasSize(databaseSizeBeforeUpdate);
        StatutDisponibilite testStatutDisponibilite = statutDisponibiliteList.get(statutDisponibiliteList.size() - 1);
        assertThat(testStatutDisponibilite.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingStatutDisponibilite() throws Exception {
        int databaseSizeBeforeUpdate = statutDisponibiliteRepository.findAll().size();

        // Create the StatutDisponibilite

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatutDisponibiliteMockMvc.perform(put("/api/statut-disponibilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statutDisponibilite)))
            .andExpect(status().isBadRequest());

        // Validate the StatutDisponibilite in the database
        List<StatutDisponibilite> statutDisponibiliteList = statutDisponibiliteRepository.findAll();
        assertThat(statutDisponibiliteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatutDisponibilite() throws Exception {
        // Initialize the database
        statutDisponibiliteRepository.saveAndFlush(statutDisponibilite);

        int databaseSizeBeforeDelete = statutDisponibiliteRepository.findAll().size();

        // Delete the statutDisponibilite
        restStatutDisponibiliteMockMvc.perform(delete("/api/statut-disponibilites/{id}", statutDisponibilite.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StatutDisponibilite> statutDisponibiliteList = statutDisponibiliteRepository.findAll();
        assertThat(statutDisponibiliteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatutDisponibilite.class);
        StatutDisponibilite statutDisponibilite1 = new StatutDisponibilite();
        statutDisponibilite1.setId(1L);
        StatutDisponibilite statutDisponibilite2 = new StatutDisponibilite();
        statutDisponibilite2.setId(statutDisponibilite1.getId());
        assertThat(statutDisponibilite1).isEqualTo(statutDisponibilite2);
        statutDisponibilite2.setId(2L);
        assertThat(statutDisponibilite1).isNotEqualTo(statutDisponibilite2);
        statutDisponibilite1.setId(null);
        assertThat(statutDisponibilite1).isNotEqualTo(statutDisponibilite2);
    }
}
