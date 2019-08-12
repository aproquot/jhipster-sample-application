package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Rv;
import io.github.jhipster.application.repository.RvRepository;
import io.github.jhipster.application.service.RvService;
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
 * Integration tests for the {@link RvResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class RvResourceIT {

    private static final String DEFAULT_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_AGENCE = "AAAAAAAAAA";
    private static final String UPDATED_AGENCE = "BBBBBBBBBB";

    private static final String DEFAULT_OBJET = "AAAAAAAAAA";
    private static final String UPDATED_OBJET = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_RV = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RV = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_RV = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_CREATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATION = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_CREATION = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_DATE_MODIFICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MODIFICATION = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_MODIFICATION = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_COMMERCIAL = "AAAAAAAAAA";
    private static final String UPDATED_COMMERCIAL = "BBBBBBBBBB";

    @Autowired
    private RvRepository rvRepository;

    @Autowired
    private RvService rvService;

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

    private MockMvc restRvMockMvc;

    private Rv rv;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RvResource rvResource = new RvResource(rvService);
        this.restRvMockMvc = MockMvcBuilders.standaloneSetup(rvResource)
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
    public static Rv createEntity(EntityManager em) {
        Rv rv = new Rv()
            .client(DEFAULT_CLIENT)
            .agence(DEFAULT_AGENCE)
            .objet(DEFAULT_OBJET)
            .dateRv(DEFAULT_DATE_RV)
            .description(DEFAULT_DESCRIPTION)
            .dateCreation(DEFAULT_DATE_CREATION)
            .dateModification(DEFAULT_DATE_MODIFICATION)
            .commercial(DEFAULT_COMMERCIAL);
        return rv;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rv createUpdatedEntity(EntityManager em) {
        Rv rv = new Rv()
            .client(UPDATED_CLIENT)
            .agence(UPDATED_AGENCE)
            .objet(UPDATED_OBJET)
            .dateRv(UPDATED_DATE_RV)
            .description(UPDATED_DESCRIPTION)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateModification(UPDATED_DATE_MODIFICATION)
            .commercial(UPDATED_COMMERCIAL);
        return rv;
    }

    @BeforeEach
    public void initTest() {
        rv = createEntity(em);
    }

    @Test
    @Transactional
    public void createRv() throws Exception {
        int databaseSizeBeforeCreate = rvRepository.findAll().size();

        // Create the Rv
        restRvMockMvc.perform(post("/api/rvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rv)))
            .andExpect(status().isCreated());

        // Validate the Rv in the database
        List<Rv> rvList = rvRepository.findAll();
        assertThat(rvList).hasSize(databaseSizeBeforeCreate + 1);
        Rv testRv = rvList.get(rvList.size() - 1);
        assertThat(testRv.getClient()).isEqualTo(DEFAULT_CLIENT);
        assertThat(testRv.getAgence()).isEqualTo(DEFAULT_AGENCE);
        assertThat(testRv.getObjet()).isEqualTo(DEFAULT_OBJET);
        assertThat(testRv.getDateRv()).isEqualTo(DEFAULT_DATE_RV);
        assertThat(testRv.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRv.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
        assertThat(testRv.getDateModification()).isEqualTo(DEFAULT_DATE_MODIFICATION);
        assertThat(testRv.getCommercial()).isEqualTo(DEFAULT_COMMERCIAL);
    }

    @Test
    @Transactional
    public void createRvWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rvRepository.findAll().size();

        // Create the Rv with an existing ID
        rv.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRvMockMvc.perform(post("/api/rvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rv)))
            .andExpect(status().isBadRequest());

        // Validate the Rv in the database
        List<Rv> rvList = rvRepository.findAll();
        assertThat(rvList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRvs() throws Exception {
        // Initialize the database
        rvRepository.saveAndFlush(rv);

        // Get all the rvList
        restRvMockMvc.perform(get("/api/rvs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rv.getId().intValue())))
            .andExpect(jsonPath("$.[*].client").value(hasItem(DEFAULT_CLIENT.toString())))
            .andExpect(jsonPath("$.[*].agence").value(hasItem(DEFAULT_AGENCE.toString())))
            .andExpect(jsonPath("$.[*].objet").value(hasItem(DEFAULT_OBJET.toString())))
            .andExpect(jsonPath("$.[*].dateRv").value(hasItem(DEFAULT_DATE_RV.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
            .andExpect(jsonPath("$.[*].dateModification").value(hasItem(DEFAULT_DATE_MODIFICATION.toString())))
            .andExpect(jsonPath("$.[*].commercial").value(hasItem(DEFAULT_COMMERCIAL.toString())));
    }
    
    @Test
    @Transactional
    public void getRv() throws Exception {
        // Initialize the database
        rvRepository.saveAndFlush(rv);

        // Get the rv
        restRvMockMvc.perform(get("/api/rvs/{id}", rv.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rv.getId().intValue()))
            .andExpect(jsonPath("$.client").value(DEFAULT_CLIENT.toString()))
            .andExpect(jsonPath("$.agence").value(DEFAULT_AGENCE.toString()))
            .andExpect(jsonPath("$.objet").value(DEFAULT_OBJET.toString()))
            .andExpect(jsonPath("$.dateRv").value(DEFAULT_DATE_RV.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.toString()))
            .andExpect(jsonPath("$.dateModification").value(DEFAULT_DATE_MODIFICATION.toString()))
            .andExpect(jsonPath("$.commercial").value(DEFAULT_COMMERCIAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRv() throws Exception {
        // Get the rv
        restRvMockMvc.perform(get("/api/rvs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRv() throws Exception {
        // Initialize the database
        rvService.save(rv);

        int databaseSizeBeforeUpdate = rvRepository.findAll().size();

        // Update the rv
        Rv updatedRv = rvRepository.findById(rv.getId()).get();
        // Disconnect from session so that the updates on updatedRv are not directly saved in db
        em.detach(updatedRv);
        updatedRv
            .client(UPDATED_CLIENT)
            .agence(UPDATED_AGENCE)
            .objet(UPDATED_OBJET)
            .dateRv(UPDATED_DATE_RV)
            .description(UPDATED_DESCRIPTION)
            .dateCreation(UPDATED_DATE_CREATION)
            .dateModification(UPDATED_DATE_MODIFICATION)
            .commercial(UPDATED_COMMERCIAL);

        restRvMockMvc.perform(put("/api/rvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRv)))
            .andExpect(status().isOk());

        // Validate the Rv in the database
        List<Rv> rvList = rvRepository.findAll();
        assertThat(rvList).hasSize(databaseSizeBeforeUpdate);
        Rv testRv = rvList.get(rvList.size() - 1);
        assertThat(testRv.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testRv.getAgence()).isEqualTo(UPDATED_AGENCE);
        assertThat(testRv.getObjet()).isEqualTo(UPDATED_OBJET);
        assertThat(testRv.getDateRv()).isEqualTo(UPDATED_DATE_RV);
        assertThat(testRv.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRv.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
        assertThat(testRv.getDateModification()).isEqualTo(UPDATED_DATE_MODIFICATION);
        assertThat(testRv.getCommercial()).isEqualTo(UPDATED_COMMERCIAL);
    }

    @Test
    @Transactional
    public void updateNonExistingRv() throws Exception {
        int databaseSizeBeforeUpdate = rvRepository.findAll().size();

        // Create the Rv

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRvMockMvc.perform(put("/api/rvs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rv)))
            .andExpect(status().isBadRequest());

        // Validate the Rv in the database
        List<Rv> rvList = rvRepository.findAll();
        assertThat(rvList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRv() throws Exception {
        // Initialize the database
        rvService.save(rv);

        int databaseSizeBeforeDelete = rvRepository.findAll().size();

        // Delete the rv
        restRvMockMvc.perform(delete("/api/rvs/{id}", rv.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Rv> rvList = rvRepository.findAll();
        assertThat(rvList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rv.class);
        Rv rv1 = new Rv();
        rv1.setId(1L);
        Rv rv2 = new Rv();
        rv2.setId(rv1.getId());
        assertThat(rv1).isEqualTo(rv2);
        rv2.setId(2L);
        assertThat(rv1).isNotEqualTo(rv2);
        rv1.setId(null);
        assertThat(rv1).isNotEqualTo(rv2);
    }
}
