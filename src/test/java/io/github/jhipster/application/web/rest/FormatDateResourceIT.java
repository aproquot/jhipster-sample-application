package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.FormatDate;
import io.github.jhipster.application.repository.FormatDateRepository;
import io.github.jhipster.application.service.FormatDateService;
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
 * Integration tests for the {@link FormatDateResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class FormatDateResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_FORMAT = "AAAAAAAAAA";
    private static final String UPDATED_FORMAT = "BBBBBBBBBB";

    @Autowired
    private FormatDateRepository formatDateRepository;

    @Autowired
    private FormatDateService formatDateService;

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

    private MockMvc restFormatDateMockMvc;

    private FormatDate formatDate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FormatDateResource formatDateResource = new FormatDateResource(formatDateService);
        this.restFormatDateMockMvc = MockMvcBuilders.standaloneSetup(formatDateResource)
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
    public static FormatDate createEntity(EntityManager em) {
        FormatDate formatDate = new FormatDate()
            .libelle(DEFAULT_LIBELLE)
            .format(DEFAULT_FORMAT);
        return formatDate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormatDate createUpdatedEntity(EntityManager em) {
        FormatDate formatDate = new FormatDate()
            .libelle(UPDATED_LIBELLE)
            .format(UPDATED_FORMAT);
        return formatDate;
    }

    @BeforeEach
    public void initTest() {
        formatDate = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormatDate() throws Exception {
        int databaseSizeBeforeCreate = formatDateRepository.findAll().size();

        // Create the FormatDate
        restFormatDateMockMvc.perform(post("/api/format-dates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formatDate)))
            .andExpect(status().isCreated());

        // Validate the FormatDate in the database
        List<FormatDate> formatDateList = formatDateRepository.findAll();
        assertThat(formatDateList).hasSize(databaseSizeBeforeCreate + 1);
        FormatDate testFormatDate = formatDateList.get(formatDateList.size() - 1);
        assertThat(testFormatDate.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testFormatDate.getFormat()).isEqualTo(DEFAULT_FORMAT);
    }

    @Test
    @Transactional
    public void createFormatDateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formatDateRepository.findAll().size();

        // Create the FormatDate with an existing ID
        formatDate.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormatDateMockMvc.perform(post("/api/format-dates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formatDate)))
            .andExpect(status().isBadRequest());

        // Validate the FormatDate in the database
        List<FormatDate> formatDateList = formatDateRepository.findAll();
        assertThat(formatDateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFormatDates() throws Exception {
        // Initialize the database
        formatDateRepository.saveAndFlush(formatDate);

        // Get all the formatDateList
        restFormatDateMockMvc.perform(get("/api/format-dates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formatDate.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].format").value(hasItem(DEFAULT_FORMAT.toString())));
    }
    
    @Test
    @Transactional
    public void getFormatDate() throws Exception {
        // Initialize the database
        formatDateRepository.saveAndFlush(formatDate);

        // Get the formatDate
        restFormatDateMockMvc.perform(get("/api/format-dates/{id}", formatDate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(formatDate.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.format").value(DEFAULT_FORMAT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFormatDate() throws Exception {
        // Get the formatDate
        restFormatDateMockMvc.perform(get("/api/format-dates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormatDate() throws Exception {
        // Initialize the database
        formatDateService.save(formatDate);

        int databaseSizeBeforeUpdate = formatDateRepository.findAll().size();

        // Update the formatDate
        FormatDate updatedFormatDate = formatDateRepository.findById(formatDate.getId()).get();
        // Disconnect from session so that the updates on updatedFormatDate are not directly saved in db
        em.detach(updatedFormatDate);
        updatedFormatDate
            .libelle(UPDATED_LIBELLE)
            .format(UPDATED_FORMAT);

        restFormatDateMockMvc.perform(put("/api/format-dates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormatDate)))
            .andExpect(status().isOk());

        // Validate the FormatDate in the database
        List<FormatDate> formatDateList = formatDateRepository.findAll();
        assertThat(formatDateList).hasSize(databaseSizeBeforeUpdate);
        FormatDate testFormatDate = formatDateList.get(formatDateList.size() - 1);
        assertThat(testFormatDate.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testFormatDate.getFormat()).isEqualTo(UPDATED_FORMAT);
    }

    @Test
    @Transactional
    public void updateNonExistingFormatDate() throws Exception {
        int databaseSizeBeforeUpdate = formatDateRepository.findAll().size();

        // Create the FormatDate

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormatDateMockMvc.perform(put("/api/format-dates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formatDate)))
            .andExpect(status().isBadRequest());

        // Validate the FormatDate in the database
        List<FormatDate> formatDateList = formatDateRepository.findAll();
        assertThat(formatDateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFormatDate() throws Exception {
        // Initialize the database
        formatDateService.save(formatDate);

        int databaseSizeBeforeDelete = formatDateRepository.findAll().size();

        // Delete the formatDate
        restFormatDateMockMvc.perform(delete("/api/format-dates/{id}", formatDate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormatDate> formatDateList = formatDateRepository.findAll();
        assertThat(formatDateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormatDate.class);
        FormatDate formatDate1 = new FormatDate();
        formatDate1.setId(1L);
        FormatDate formatDate2 = new FormatDate();
        formatDate2.setId(formatDate1.getId());
        assertThat(formatDate1).isEqualTo(formatDate2);
        formatDate2.setId(2L);
        assertThat(formatDate1).isNotEqualTo(formatDate2);
        formatDate1.setId(null);
        assertThat(formatDate1).isNotEqualTo(formatDate2);
    }
}
