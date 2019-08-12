package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Groupe;
import io.github.jhipster.application.repository.GroupeRepository;
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
 * Integration tests for the {@link GroupeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class GroupeResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private GroupeRepository groupeRepository;

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

    private MockMvc restGroupeMockMvc;

    private Groupe groupe;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroupeResource groupeResource = new GroupeResource(groupeRepository);
        this.restGroupeMockMvc = MockMvcBuilders.standaloneSetup(groupeResource)
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
    public static Groupe createEntity(EntityManager em) {
        Groupe groupe = new Groupe()
            .libelle(DEFAULT_LIBELLE);
        return groupe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Groupe createUpdatedEntity(EntityManager em) {
        Groupe groupe = new Groupe()
            .libelle(UPDATED_LIBELLE);
        return groupe;
    }

    @BeforeEach
    public void initTest() {
        groupe = createEntity(em);
    }

    @Test
    @Transactional
    public void createGroupe() throws Exception {
        int databaseSizeBeforeCreate = groupeRepository.findAll().size();

        // Create the Groupe
        restGroupeMockMvc.perform(post("/api/groupes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupe)))
            .andExpect(status().isCreated());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeCreate + 1);
        Groupe testGroupe = groupeList.get(groupeList.size() - 1);
        assertThat(testGroupe.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createGroupeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupeRepository.findAll().size();

        // Create the Groupe with an existing ID
        groupe.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupeMockMvc.perform(post("/api/groupes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupe)))
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGroupes() throws Exception {
        // Initialize the database
        groupeRepository.saveAndFlush(groupe);

        // Get all the groupeList
        restGroupeMockMvc.perform(get("/api/groupes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupe.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getGroupe() throws Exception {
        // Initialize the database
        groupeRepository.saveAndFlush(groupe);

        // Get the groupe
        restGroupeMockMvc.perform(get("/api/groupes/{id}", groupe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(groupe.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGroupe() throws Exception {
        // Get the groupe
        restGroupeMockMvc.perform(get("/api/groupes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroupe() throws Exception {
        // Initialize the database
        groupeRepository.saveAndFlush(groupe);

        int databaseSizeBeforeUpdate = groupeRepository.findAll().size();

        // Update the groupe
        Groupe updatedGroupe = groupeRepository.findById(groupe.getId()).get();
        // Disconnect from session so that the updates on updatedGroupe are not directly saved in db
        em.detach(updatedGroupe);
        updatedGroupe
            .libelle(UPDATED_LIBELLE);

        restGroupeMockMvc.perform(put("/api/groupes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGroupe)))
            .andExpect(status().isOk());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeUpdate);
        Groupe testGroupe = groupeList.get(groupeList.size() - 1);
        assertThat(testGroupe.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingGroupe() throws Exception {
        int databaseSizeBeforeUpdate = groupeRepository.findAll().size();

        // Create the Groupe

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupeMockMvc.perform(put("/api/groupes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupe)))
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGroupe() throws Exception {
        // Initialize the database
        groupeRepository.saveAndFlush(groupe);

        int databaseSizeBeforeDelete = groupeRepository.findAll().size();

        // Delete the groupe
        restGroupeMockMvc.perform(delete("/api/groupes/{id}", groupe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Groupe.class);
        Groupe groupe1 = new Groupe();
        groupe1.setId(1L);
        Groupe groupe2 = new Groupe();
        groupe2.setId(groupe1.getId());
        assertThat(groupe1).isEqualTo(groupe2);
        groupe2.setId(2L);
        assertThat(groupe1).isNotEqualTo(groupe2);
        groupe1.setId(null);
        assertThat(groupe1).isNotEqualTo(groupe2);
    }
}
