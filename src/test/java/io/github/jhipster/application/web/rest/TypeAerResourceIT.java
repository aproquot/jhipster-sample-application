package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.TypeAer;
import io.github.jhipster.application.repository.TypeAerRepository;
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
 * Integration tests for the {@link TypeAerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class TypeAerResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypeAerRepository typeAerRepository;

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

    private MockMvc restTypeAerMockMvc;

    private TypeAer typeAer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeAerResource typeAerResource = new TypeAerResource(typeAerRepository);
        this.restTypeAerMockMvc = MockMvcBuilders.standaloneSetup(typeAerResource)
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
    public static TypeAer createEntity(EntityManager em) {
        TypeAer typeAer = new TypeAer()
            .libelle(DEFAULT_LIBELLE);
        return typeAer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeAer createUpdatedEntity(EntityManager em) {
        TypeAer typeAer = new TypeAer()
            .libelle(UPDATED_LIBELLE);
        return typeAer;
    }

    @BeforeEach
    public void initTest() {
        typeAer = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeAer() throws Exception {
        int databaseSizeBeforeCreate = typeAerRepository.findAll().size();

        // Create the TypeAer
        restTypeAerMockMvc.perform(post("/api/type-aers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeAer)))
            .andExpect(status().isCreated());

        // Validate the TypeAer in the database
        List<TypeAer> typeAerList = typeAerRepository.findAll();
        assertThat(typeAerList).hasSize(databaseSizeBeforeCreate + 1);
        TypeAer testTypeAer = typeAerList.get(typeAerList.size() - 1);
        assertThat(testTypeAer.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeAerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeAerRepository.findAll().size();

        // Create the TypeAer with an existing ID
        typeAer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeAerMockMvc.perform(post("/api/type-aers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeAer)))
            .andExpect(status().isBadRequest());

        // Validate the TypeAer in the database
        List<TypeAer> typeAerList = typeAerRepository.findAll();
        assertThat(typeAerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeAers() throws Exception {
        // Initialize the database
        typeAerRepository.saveAndFlush(typeAer);

        // Get all the typeAerList
        restTypeAerMockMvc.perform(get("/api/type-aers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeAer.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeAer() throws Exception {
        // Initialize the database
        typeAerRepository.saveAndFlush(typeAer);

        // Get the typeAer
        restTypeAerMockMvc.perform(get("/api/type-aers/{id}", typeAer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeAer.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeAer() throws Exception {
        // Get the typeAer
        restTypeAerMockMvc.perform(get("/api/type-aers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeAer() throws Exception {
        // Initialize the database
        typeAerRepository.saveAndFlush(typeAer);

        int databaseSizeBeforeUpdate = typeAerRepository.findAll().size();

        // Update the typeAer
        TypeAer updatedTypeAer = typeAerRepository.findById(typeAer.getId()).get();
        // Disconnect from session so that the updates on updatedTypeAer are not directly saved in db
        em.detach(updatedTypeAer);
        updatedTypeAer
            .libelle(UPDATED_LIBELLE);

        restTypeAerMockMvc.perform(put("/api/type-aers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeAer)))
            .andExpect(status().isOk());

        // Validate the TypeAer in the database
        List<TypeAer> typeAerList = typeAerRepository.findAll();
        assertThat(typeAerList).hasSize(databaseSizeBeforeUpdate);
        TypeAer testTypeAer = typeAerList.get(typeAerList.size() - 1);
        assertThat(testTypeAer.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeAer() throws Exception {
        int databaseSizeBeforeUpdate = typeAerRepository.findAll().size();

        // Create the TypeAer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeAerMockMvc.perform(put("/api/type-aers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeAer)))
            .andExpect(status().isBadRequest());

        // Validate the TypeAer in the database
        List<TypeAer> typeAerList = typeAerRepository.findAll();
        assertThat(typeAerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeAer() throws Exception {
        // Initialize the database
        typeAerRepository.saveAndFlush(typeAer);

        int databaseSizeBeforeDelete = typeAerRepository.findAll().size();

        // Delete the typeAer
        restTypeAerMockMvc.perform(delete("/api/type-aers/{id}", typeAer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeAer> typeAerList = typeAerRepository.findAll();
        assertThat(typeAerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeAer.class);
        TypeAer typeAer1 = new TypeAer();
        typeAer1.setId(1L);
        TypeAer typeAer2 = new TypeAer();
        typeAer2.setId(typeAer1.getId());
        assertThat(typeAer1).isEqualTo(typeAer2);
        typeAer2.setId(2L);
        assertThat(typeAer1).isNotEqualTo(typeAer2);
        typeAer1.setId(null);
        assertThat(typeAer1).isNotEqualTo(typeAer2);
    }
}
