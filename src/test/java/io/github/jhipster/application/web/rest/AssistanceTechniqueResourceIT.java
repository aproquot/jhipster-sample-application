package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.AssistanceTechnique;
import io.github.jhipster.application.repository.AssistanceTechniqueRepository;
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
 * Integration tests for the {@link AssistanceTechniqueResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class AssistanceTechniqueResourceIT {

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

    private static final String DEFAULT_CODE_PROFIL = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PROFIL = "BBBBBBBBBB";

    private static final String DEFAULT_TJM = "AAAAAAAAAA";
    private static final String UPDATED_TJM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DEMARRAGE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEMARRAGE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_DEMARRAGE = LocalDate.ofEpochDay(-1L);

    @Autowired
    private AssistanceTechniqueRepository assistanceTechniqueRepository;

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

    private MockMvc restAssistanceTechniqueMockMvc;

    private AssistanceTechnique assistanceTechnique;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AssistanceTechniqueResource assistanceTechniqueResource = new AssistanceTechniqueResource(assistanceTechniqueRepository);
        this.restAssistanceTechniqueMockMvc = MockMvcBuilders.standaloneSetup(assistanceTechniqueResource)
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
    public static AssistanceTechnique createEntity(EntityManager em) {
        AssistanceTechnique assistanceTechnique = new AssistanceTechnique()
            .description(DEFAULT_DESCRIPTION)
            .dateReception(DEFAULT_DATE_RECEPTION)
            .dateCloture(DEFAULT_DATE_CLOTURE)
            .dateReponse(DEFAULT_DATE_REPONSE)
            .action(DEFAULT_ACTION)
            .ao(DEFAULT_AO)
            .codeProfil(DEFAULT_CODE_PROFIL)
            .tjm(DEFAULT_TJM)
            .dateDemarrage(DEFAULT_DATE_DEMARRAGE);
        return assistanceTechnique;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AssistanceTechnique createUpdatedEntity(EntityManager em) {
        AssistanceTechnique assistanceTechnique = new AssistanceTechnique()
            .description(UPDATED_DESCRIPTION)
            .dateReception(UPDATED_DATE_RECEPTION)
            .dateCloture(UPDATED_DATE_CLOTURE)
            .dateReponse(UPDATED_DATE_REPONSE)
            .action(UPDATED_ACTION)
            .ao(UPDATED_AO)
            .codeProfil(UPDATED_CODE_PROFIL)
            .tjm(UPDATED_TJM)
            .dateDemarrage(UPDATED_DATE_DEMARRAGE);
        return assistanceTechnique;
    }

    @BeforeEach
    public void initTest() {
        assistanceTechnique = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssistanceTechnique() throws Exception {
        int databaseSizeBeforeCreate = assistanceTechniqueRepository.findAll().size();

        // Create the AssistanceTechnique
        restAssistanceTechniqueMockMvc.perform(post("/api/assistance-techniques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assistanceTechnique)))
            .andExpect(status().isCreated());

        // Validate the AssistanceTechnique in the database
        List<AssistanceTechnique> assistanceTechniqueList = assistanceTechniqueRepository.findAll();
        assertThat(assistanceTechniqueList).hasSize(databaseSizeBeforeCreate + 1);
        AssistanceTechnique testAssistanceTechnique = assistanceTechniqueList.get(assistanceTechniqueList.size() - 1);
        assertThat(testAssistanceTechnique.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAssistanceTechnique.getDateReception()).isEqualTo(DEFAULT_DATE_RECEPTION);
        assertThat(testAssistanceTechnique.getDateCloture()).isEqualTo(DEFAULT_DATE_CLOTURE);
        assertThat(testAssistanceTechnique.getDateReponse()).isEqualTo(DEFAULT_DATE_REPONSE);
        assertThat(testAssistanceTechnique.getAction()).isEqualTo(DEFAULT_ACTION);
        assertThat(testAssistanceTechnique.getAo()).isEqualTo(DEFAULT_AO);
        assertThat(testAssistanceTechnique.getCodeProfil()).isEqualTo(DEFAULT_CODE_PROFIL);
        assertThat(testAssistanceTechnique.getTjm()).isEqualTo(DEFAULT_TJM);
        assertThat(testAssistanceTechnique.getDateDemarrage()).isEqualTo(DEFAULT_DATE_DEMARRAGE);
    }

    @Test
    @Transactional
    public void createAssistanceTechniqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assistanceTechniqueRepository.findAll().size();

        // Create the AssistanceTechnique with an existing ID
        assistanceTechnique.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssistanceTechniqueMockMvc.perform(post("/api/assistance-techniques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assistanceTechnique)))
            .andExpect(status().isBadRequest());

        // Validate the AssistanceTechnique in the database
        List<AssistanceTechnique> assistanceTechniqueList = assistanceTechniqueRepository.findAll();
        assertThat(assistanceTechniqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAssistanceTechniques() throws Exception {
        // Initialize the database
        assistanceTechniqueRepository.saveAndFlush(assistanceTechnique);

        // Get all the assistanceTechniqueList
        restAssistanceTechniqueMockMvc.perform(get("/api/assistance-techniques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assistanceTechnique.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateReception").value(hasItem(DEFAULT_DATE_RECEPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCloture").value(hasItem(DEFAULT_DATE_CLOTURE.toString())))
            .andExpect(jsonPath("$.[*].dateReponse").value(hasItem(DEFAULT_DATE_REPONSE.toString())))
            .andExpect(jsonPath("$.[*].action").value(hasItem(DEFAULT_ACTION.toString())))
            .andExpect(jsonPath("$.[*].ao").value(hasItem(DEFAULT_AO.toString())))
            .andExpect(jsonPath("$.[*].codeProfil").value(hasItem(DEFAULT_CODE_PROFIL.toString())))
            .andExpect(jsonPath("$.[*].tjm").value(hasItem(DEFAULT_TJM.toString())))
            .andExpect(jsonPath("$.[*].dateDemarrage").value(hasItem(DEFAULT_DATE_DEMARRAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getAssistanceTechnique() throws Exception {
        // Initialize the database
        assistanceTechniqueRepository.saveAndFlush(assistanceTechnique);

        // Get the assistanceTechnique
        restAssistanceTechniqueMockMvc.perform(get("/api/assistance-techniques/{id}", assistanceTechnique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(assistanceTechnique.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateReception").value(DEFAULT_DATE_RECEPTION.toString()))
            .andExpect(jsonPath("$.dateCloture").value(DEFAULT_DATE_CLOTURE.toString()))
            .andExpect(jsonPath("$.dateReponse").value(DEFAULT_DATE_REPONSE.toString()))
            .andExpect(jsonPath("$.action").value(DEFAULT_ACTION.toString()))
            .andExpect(jsonPath("$.ao").value(DEFAULT_AO.toString()))
            .andExpect(jsonPath("$.codeProfil").value(DEFAULT_CODE_PROFIL.toString()))
            .andExpect(jsonPath("$.tjm").value(DEFAULT_TJM.toString()))
            .andExpect(jsonPath("$.dateDemarrage").value(DEFAULT_DATE_DEMARRAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAssistanceTechnique() throws Exception {
        // Get the assistanceTechnique
        restAssistanceTechniqueMockMvc.perform(get("/api/assistance-techniques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssistanceTechnique() throws Exception {
        // Initialize the database
        assistanceTechniqueRepository.saveAndFlush(assistanceTechnique);

        int databaseSizeBeforeUpdate = assistanceTechniqueRepository.findAll().size();

        // Update the assistanceTechnique
        AssistanceTechnique updatedAssistanceTechnique = assistanceTechniqueRepository.findById(assistanceTechnique.getId()).get();
        // Disconnect from session so that the updates on updatedAssistanceTechnique are not directly saved in db
        em.detach(updatedAssistanceTechnique);
        updatedAssistanceTechnique
            .description(UPDATED_DESCRIPTION)
            .dateReception(UPDATED_DATE_RECEPTION)
            .dateCloture(UPDATED_DATE_CLOTURE)
            .dateReponse(UPDATED_DATE_REPONSE)
            .action(UPDATED_ACTION)
            .ao(UPDATED_AO)
            .codeProfil(UPDATED_CODE_PROFIL)
            .tjm(UPDATED_TJM)
            .dateDemarrage(UPDATED_DATE_DEMARRAGE);

        restAssistanceTechniqueMockMvc.perform(put("/api/assistance-techniques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAssistanceTechnique)))
            .andExpect(status().isOk());

        // Validate the AssistanceTechnique in the database
        List<AssistanceTechnique> assistanceTechniqueList = assistanceTechniqueRepository.findAll();
        assertThat(assistanceTechniqueList).hasSize(databaseSizeBeforeUpdate);
        AssistanceTechnique testAssistanceTechnique = assistanceTechniqueList.get(assistanceTechniqueList.size() - 1);
        assertThat(testAssistanceTechnique.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAssistanceTechnique.getDateReception()).isEqualTo(UPDATED_DATE_RECEPTION);
        assertThat(testAssistanceTechnique.getDateCloture()).isEqualTo(UPDATED_DATE_CLOTURE);
        assertThat(testAssistanceTechnique.getDateReponse()).isEqualTo(UPDATED_DATE_REPONSE);
        assertThat(testAssistanceTechnique.getAction()).isEqualTo(UPDATED_ACTION);
        assertThat(testAssistanceTechnique.getAo()).isEqualTo(UPDATED_AO);
        assertThat(testAssistanceTechnique.getCodeProfil()).isEqualTo(UPDATED_CODE_PROFIL);
        assertThat(testAssistanceTechnique.getTjm()).isEqualTo(UPDATED_TJM);
        assertThat(testAssistanceTechnique.getDateDemarrage()).isEqualTo(UPDATED_DATE_DEMARRAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingAssistanceTechnique() throws Exception {
        int databaseSizeBeforeUpdate = assistanceTechniqueRepository.findAll().size();

        // Create the AssistanceTechnique

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssistanceTechniqueMockMvc.perform(put("/api/assistance-techniques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(assistanceTechnique)))
            .andExpect(status().isBadRequest());

        // Validate the AssistanceTechnique in the database
        List<AssistanceTechnique> assistanceTechniqueList = assistanceTechniqueRepository.findAll();
        assertThat(assistanceTechniqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAssistanceTechnique() throws Exception {
        // Initialize the database
        assistanceTechniqueRepository.saveAndFlush(assistanceTechnique);

        int databaseSizeBeforeDelete = assistanceTechniqueRepository.findAll().size();

        // Delete the assistanceTechnique
        restAssistanceTechniqueMockMvc.perform(delete("/api/assistance-techniques/{id}", assistanceTechnique.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AssistanceTechnique> assistanceTechniqueList = assistanceTechniqueRepository.findAll();
        assertThat(assistanceTechniqueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssistanceTechnique.class);
        AssistanceTechnique assistanceTechnique1 = new AssistanceTechnique();
        assistanceTechnique1.setId(1L);
        AssistanceTechnique assistanceTechnique2 = new AssistanceTechnique();
        assistanceTechnique2.setId(assistanceTechnique1.getId());
        assertThat(assistanceTechnique1).isEqualTo(assistanceTechnique2);
        assistanceTechnique2.setId(2L);
        assertThat(assistanceTechnique1).isNotEqualTo(assistanceTechnique2);
        assistanceTechnique1.setId(null);
        assertThat(assistanceTechnique1).isNotEqualTo(assistanceTechnique2);
    }
}
