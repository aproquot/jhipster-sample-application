package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Disponibilite;
import io.github.jhipster.application.repository.DisponibiliteRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DisponibiliteResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class DisponibiliteResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_COMPETENCE = "AAAAAAAAAA";
    private static final String UPDATED_COMPETENCE = "BBBBBBBBBB";

    private static final String DEFAULT_LOGEMENT = "AAAAAAAAAA";
    private static final String UPDATED_LOGEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_OCCUPATION = "AAAAAAAAAA";
    private static final String UPDATED_OCCUPATION = "BBBBBBBBBB";

    private static final String DEFAULT_AFFAIRE_EN_VUE = "AAAAAAAAAA";
    private static final String UPDATED_AFFAIRE_EN_VUE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IC_DOMICILE = false;
    private static final Boolean UPDATED_IC_DOMICILE = true;

    @Autowired
    private DisponibiliteRepository disponibiliteRepository;

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

    private MockMvc restDisponibiliteMockMvc;

    private Disponibilite disponibilite;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DisponibiliteResource disponibiliteResource = new DisponibiliteResource(disponibiliteRepository);
        this.restDisponibiliteMockMvc = MockMvcBuilders.standaloneSetup(disponibiliteResource)
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
    public static Disponibilite createEntity(EntityManager em) {
        Disponibilite disponibilite = new Disponibilite()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .date(DEFAULT_DATE)
            .competence(DEFAULT_COMPETENCE)
            .logement(DEFAULT_LOGEMENT)
            .occupation(DEFAULT_OCCUPATION)
            .affaireEnVue(DEFAULT_AFFAIRE_EN_VUE)
            .icDomicile(DEFAULT_IC_DOMICILE);
        return disponibilite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Disponibilite createUpdatedEntity(EntityManager em) {
        Disponibilite disponibilite = new Disponibilite()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .date(UPDATED_DATE)
            .competence(UPDATED_COMPETENCE)
            .logement(UPDATED_LOGEMENT)
            .occupation(UPDATED_OCCUPATION)
            .affaireEnVue(UPDATED_AFFAIRE_EN_VUE)
            .icDomicile(UPDATED_IC_DOMICILE);
        return disponibilite;
    }

    @BeforeEach
    public void initTest() {
        disponibilite = createEntity(em);
    }

    @Test
    @Transactional
    public void createDisponibilite() throws Exception {
        int databaseSizeBeforeCreate = disponibiliteRepository.findAll().size();

        // Create the Disponibilite
        restDisponibiliteMockMvc.perform(post("/api/disponibilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilite)))
            .andExpect(status().isCreated());

        // Validate the Disponibilite in the database
        List<Disponibilite> disponibiliteList = disponibiliteRepository.findAll();
        assertThat(disponibiliteList).hasSize(databaseSizeBeforeCreate + 1);
        Disponibilite testDisponibilite = disponibiliteList.get(disponibiliteList.size() - 1);
        assertThat(testDisponibilite.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testDisponibilite.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testDisponibilite.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testDisponibilite.getCompetence()).isEqualTo(DEFAULT_COMPETENCE);
        assertThat(testDisponibilite.getLogement()).isEqualTo(DEFAULT_LOGEMENT);
        assertThat(testDisponibilite.getOccupation()).isEqualTo(DEFAULT_OCCUPATION);
        assertThat(testDisponibilite.getAffaireEnVue()).isEqualTo(DEFAULT_AFFAIRE_EN_VUE);
        assertThat(testDisponibilite.isIcDomicile()).isEqualTo(DEFAULT_IC_DOMICILE);
    }

    @Test
    @Transactional
    public void createDisponibiliteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = disponibiliteRepository.findAll().size();

        // Create the Disponibilite with an existing ID
        disponibilite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisponibiliteMockMvc.perform(post("/api/disponibilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilite)))
            .andExpect(status().isBadRequest());

        // Validate the Disponibilite in the database
        List<Disponibilite> disponibiliteList = disponibiliteRepository.findAll();
        assertThat(disponibiliteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDisponibilites() throws Exception {
        // Initialize the database
        disponibiliteRepository.saveAndFlush(disponibilite);

        // Get all the disponibiliteList
        restDisponibiliteMockMvc.perform(get("/api/disponibilites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disponibilite.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].competence").value(hasItem(DEFAULT_COMPETENCE.toString())))
            .andExpect(jsonPath("$.[*].logement").value(hasItem(DEFAULT_LOGEMENT.toString())))
            .andExpect(jsonPath("$.[*].occupation").value(hasItem(DEFAULT_OCCUPATION.toString())))
            .andExpect(jsonPath("$.[*].affaireEnVue").value(hasItem(DEFAULT_AFFAIRE_EN_VUE.toString())))
            .andExpect(jsonPath("$.[*].icDomicile").value(hasItem(DEFAULT_IC_DOMICILE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDisponibilite() throws Exception {
        // Initialize the database
        disponibiliteRepository.saveAndFlush(disponibilite);

        // Get the disponibilite
        restDisponibiliteMockMvc.perform(get("/api/disponibilites/{id}", disponibilite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(disponibilite.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.competence").value(DEFAULT_COMPETENCE.toString()))
            .andExpect(jsonPath("$.logement").value(DEFAULT_LOGEMENT.toString()))
            .andExpect(jsonPath("$.occupation").value(DEFAULT_OCCUPATION.toString()))
            .andExpect(jsonPath("$.affaireEnVue").value(DEFAULT_AFFAIRE_EN_VUE.toString()))
            .andExpect(jsonPath("$.icDomicile").value(DEFAULT_IC_DOMICILE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDisponibilite() throws Exception {
        // Get the disponibilite
        restDisponibiliteMockMvc.perform(get("/api/disponibilites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDisponibilite() throws Exception {
        // Initialize the database
        disponibiliteRepository.saveAndFlush(disponibilite);

        int databaseSizeBeforeUpdate = disponibiliteRepository.findAll().size();

        // Update the disponibilite
        Disponibilite updatedDisponibilite = disponibiliteRepository.findById(disponibilite.getId()).get();
        // Disconnect from session so that the updates on updatedDisponibilite are not directly saved in db
        em.detach(updatedDisponibilite);
        updatedDisponibilite
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .date(UPDATED_DATE)
            .competence(UPDATED_COMPETENCE)
            .logement(UPDATED_LOGEMENT)
            .occupation(UPDATED_OCCUPATION)
            .affaireEnVue(UPDATED_AFFAIRE_EN_VUE)
            .icDomicile(UPDATED_IC_DOMICILE);

        restDisponibiliteMockMvc.perform(put("/api/disponibilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDisponibilite)))
            .andExpect(status().isOk());

        // Validate the Disponibilite in the database
        List<Disponibilite> disponibiliteList = disponibiliteRepository.findAll();
        assertThat(disponibiliteList).hasSize(databaseSizeBeforeUpdate);
        Disponibilite testDisponibilite = disponibiliteList.get(disponibiliteList.size() - 1);
        assertThat(testDisponibilite.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testDisponibilite.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testDisponibilite.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testDisponibilite.getCompetence()).isEqualTo(UPDATED_COMPETENCE);
        assertThat(testDisponibilite.getLogement()).isEqualTo(UPDATED_LOGEMENT);
        assertThat(testDisponibilite.getOccupation()).isEqualTo(UPDATED_OCCUPATION);
        assertThat(testDisponibilite.getAffaireEnVue()).isEqualTo(UPDATED_AFFAIRE_EN_VUE);
        assertThat(testDisponibilite.isIcDomicile()).isEqualTo(UPDATED_IC_DOMICILE);
    }

    @Test
    @Transactional
    public void updateNonExistingDisponibilite() throws Exception {
        int databaseSizeBeforeUpdate = disponibiliteRepository.findAll().size();

        // Create the Disponibilite

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDisponibiliteMockMvc.perform(put("/api/disponibilites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilite)))
            .andExpect(status().isBadRequest());

        // Validate the Disponibilite in the database
        List<Disponibilite> disponibiliteList = disponibiliteRepository.findAll();
        assertThat(disponibiliteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDisponibilite() throws Exception {
        // Initialize the database
        disponibiliteRepository.saveAndFlush(disponibilite);

        int databaseSizeBeforeDelete = disponibiliteRepository.findAll().size();

        // Delete the disponibilite
        restDisponibiliteMockMvc.perform(delete("/api/disponibilites/{id}", disponibilite.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Disponibilite> disponibiliteList = disponibiliteRepository.findAll();
        assertThat(disponibiliteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Disponibilite.class);
        Disponibilite disponibilite1 = new Disponibilite();
        disponibilite1.setId(1L);
        Disponibilite disponibilite2 = new Disponibilite();
        disponibilite2.setId(disponibilite1.getId());
        assertThat(disponibilite1).isEqualTo(disponibilite2);
        disponibilite2.setId(2L);
        assertThat(disponibilite1).isNotEqualTo(disponibilite2);
        disponibilite1.setId(null);
        assertThat(disponibilite1).isNotEqualTo(disponibilite2);
    }
}
