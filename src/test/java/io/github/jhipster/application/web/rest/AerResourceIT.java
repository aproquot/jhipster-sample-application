package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Aer;
import io.github.jhipster.application.repository.AerRepository;
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
 * Integration tests for the {@link AerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class AerResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_RECEPTION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RECEPTION = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_RECEPTION = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_DATE_CLOTURE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CLOTURE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_CLOTURE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_DATE_REPONSE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_REPONSE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_REPONSE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_ACTION = "AAAAAAAAAA";
    private static final String UPDATED_ACTION = "BBBBBBBBBB";

    private static final String DEFAULT_AO = "AAAAAAAAAA";
    private static final String UPDATED_AO = "BBBBBBBBBB";

    private static final String DEFAULT_MONTANT = "AAAAAAAAAA";
    private static final String UPDATED_MONTANT = "BBBBBBBBBB";

    @Autowired
    private AerRepository aerRepository;

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

    private MockMvc restAerMockMvc;

    private Aer aer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AerResource aerResource = new AerResource(aerRepository);
        this.restAerMockMvc = MockMvcBuilders.standaloneSetup(aerResource)
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
    public static Aer createEntity(EntityManager em) {
        Aer aer = new Aer()
            .description(DEFAULT_DESCRIPTION)
            .dateReception(DEFAULT_DATE_RECEPTION)
            .dateCloture(DEFAULT_DATE_CLOTURE)
            .dateReponse(DEFAULT_DATE_REPONSE)
            .action(DEFAULT_ACTION)
            .ao(DEFAULT_AO)
            .montant(DEFAULT_MONTANT);
        return aer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aer createUpdatedEntity(EntityManager em) {
        Aer aer = new Aer()
            .description(UPDATED_DESCRIPTION)
            .dateReception(UPDATED_DATE_RECEPTION)
            .dateCloture(UPDATED_DATE_CLOTURE)
            .dateReponse(UPDATED_DATE_REPONSE)
            .action(UPDATED_ACTION)
            .ao(UPDATED_AO)
            .montant(UPDATED_MONTANT);
        return aer;
    }

    @BeforeEach
    public void initTest() {
        aer = createEntity(em);
    }

    @Test
    @Transactional
    public void createAer() throws Exception {
        int databaseSizeBeforeCreate = aerRepository.findAll().size();

        // Create the Aer
        restAerMockMvc.perform(post("/api/aers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aer)))
            .andExpect(status().isCreated());

        // Validate the Aer in the database
        List<Aer> aerList = aerRepository.findAll();
        assertThat(aerList).hasSize(databaseSizeBeforeCreate + 1);
        Aer testAer = aerList.get(aerList.size() - 1);
        assertThat(testAer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAer.getDateReception()).isEqualTo(DEFAULT_DATE_RECEPTION);
        assertThat(testAer.getDateCloture()).isEqualTo(DEFAULT_DATE_CLOTURE);
        assertThat(testAer.getDateReponse()).isEqualTo(DEFAULT_DATE_REPONSE);
        assertThat(testAer.getAction()).isEqualTo(DEFAULT_ACTION);
        assertThat(testAer.getAo()).isEqualTo(DEFAULT_AO);
        assertThat(testAer.getMontant()).isEqualTo(DEFAULT_MONTANT);
    }

    @Test
    @Transactional
    public void createAerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aerRepository.findAll().size();

        // Create the Aer with an existing ID
        aer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAerMockMvc.perform(post("/api/aers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aer)))
            .andExpect(status().isBadRequest());

        // Validate the Aer in the database
        List<Aer> aerList = aerRepository.findAll();
        assertThat(aerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAers() throws Exception {
        // Initialize the database
        aerRepository.saveAndFlush(aer);

        // Get all the aerList
        restAerMockMvc.perform(get("/api/aers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aer.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateReception").value(hasItem(DEFAULT_DATE_RECEPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCloture").value(hasItem(DEFAULT_DATE_CLOTURE.toString())))
            .andExpect(jsonPath("$.[*].dateReponse").value(hasItem(DEFAULT_DATE_REPONSE.toString())))
            .andExpect(jsonPath("$.[*].action").value(hasItem(DEFAULT_ACTION.toString())))
            .andExpect(jsonPath("$.[*].ao").value(hasItem(DEFAULT_AO.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.toString())));
    }
    
    @Test
    @Transactional
    public void getAer() throws Exception {
        // Initialize the database
        aerRepository.saveAndFlush(aer);

        // Get the aer
        restAerMockMvc.perform(get("/api/aers/{id}", aer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(aer.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateReception").value(DEFAULT_DATE_RECEPTION.toString()))
            .andExpect(jsonPath("$.dateCloture").value(DEFAULT_DATE_CLOTURE.toString()))
            .andExpect(jsonPath("$.dateReponse").value(DEFAULT_DATE_REPONSE.toString()))
            .andExpect(jsonPath("$.action").value(DEFAULT_ACTION.toString()))
            .andExpect(jsonPath("$.ao").value(DEFAULT_AO.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAer() throws Exception {
        // Get the aer
        restAerMockMvc.perform(get("/api/aers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAer() throws Exception {
        // Initialize the database
        aerRepository.saveAndFlush(aer);

        int databaseSizeBeforeUpdate = aerRepository.findAll().size();

        // Update the aer
        Aer updatedAer = aerRepository.findById(aer.getId()).get();
        // Disconnect from session so that the updates on updatedAer are not directly saved in db
        em.detach(updatedAer);
        updatedAer
            .description(UPDATED_DESCRIPTION)
            .dateReception(UPDATED_DATE_RECEPTION)
            .dateCloture(UPDATED_DATE_CLOTURE)
            .dateReponse(UPDATED_DATE_REPONSE)
            .action(UPDATED_ACTION)
            .ao(UPDATED_AO)
            .montant(UPDATED_MONTANT);

        restAerMockMvc.perform(put("/api/aers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAer)))
            .andExpect(status().isOk());

        // Validate the Aer in the database
        List<Aer> aerList = aerRepository.findAll();
        assertThat(aerList).hasSize(databaseSizeBeforeUpdate);
        Aer testAer = aerList.get(aerList.size() - 1);
        assertThat(testAer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAer.getDateReception()).isEqualTo(UPDATED_DATE_RECEPTION);
        assertThat(testAer.getDateCloture()).isEqualTo(UPDATED_DATE_CLOTURE);
        assertThat(testAer.getDateReponse()).isEqualTo(UPDATED_DATE_REPONSE);
        assertThat(testAer.getAction()).isEqualTo(UPDATED_ACTION);
        assertThat(testAer.getAo()).isEqualTo(UPDATED_AO);
        assertThat(testAer.getMontant()).isEqualTo(UPDATED_MONTANT);
    }

    @Test
    @Transactional
    public void updateNonExistingAer() throws Exception {
        int databaseSizeBeforeUpdate = aerRepository.findAll().size();

        // Create the Aer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAerMockMvc.perform(put("/api/aers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aer)))
            .andExpect(status().isBadRequest());

        // Validate the Aer in the database
        List<Aer> aerList = aerRepository.findAll();
        assertThat(aerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAer() throws Exception {
        // Initialize the database
        aerRepository.saveAndFlush(aer);

        int databaseSizeBeforeDelete = aerRepository.findAll().size();

        // Delete the aer
        restAerMockMvc.perform(delete("/api/aers/{id}", aer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Aer> aerList = aerRepository.findAll();
        assertThat(aerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Aer.class);
        Aer aer1 = new Aer();
        aer1.setId(1L);
        Aer aer2 = new Aer();
        aer2.setId(aer1.getId());
        assertThat(aer1).isEqualTo(aer2);
        aer2.setId(2L);
        assertThat(aer1).isNotEqualTo(aer2);
        aer1.setId(null);
        assertThat(aer1).isNotEqualTo(aer2);
    }
}
