package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.AssistanceTechnique;
import io.github.jhipster.application.repository.AssistanceTechniqueRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.AssistanceTechnique}.
 */
@RestController
@RequestMapping("/api")
public class AssistanceTechniqueResource {

    private final Logger log = LoggerFactory.getLogger(AssistanceTechniqueResource.class);

    private static final String ENTITY_NAME = "assistanceTechnique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssistanceTechniqueRepository assistanceTechniqueRepository;

    public AssistanceTechniqueResource(AssistanceTechniqueRepository assistanceTechniqueRepository) {
        this.assistanceTechniqueRepository = assistanceTechniqueRepository;
    }

    /**
     * {@code POST  /assistance-techniques} : Create a new assistanceTechnique.
     *
     * @param assistanceTechnique the assistanceTechnique to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new assistanceTechnique, or with status {@code 400 (Bad Request)} if the assistanceTechnique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assistance-techniques")
    public ResponseEntity<AssistanceTechnique> createAssistanceTechnique(@RequestBody AssistanceTechnique assistanceTechnique) throws URISyntaxException {
        log.debug("REST request to save AssistanceTechnique : {}", assistanceTechnique);
        if (assistanceTechnique.getId() != null) {
            throw new BadRequestAlertException("A new assistanceTechnique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssistanceTechnique result = assistanceTechniqueRepository.save(assistanceTechnique);
        return ResponseEntity.created(new URI("/api/assistance-techniques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /assistance-techniques} : Updates an existing assistanceTechnique.
     *
     * @param assistanceTechnique the assistanceTechnique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assistanceTechnique,
     * or with status {@code 400 (Bad Request)} if the assistanceTechnique is not valid,
     * or with status {@code 500 (Internal Server Error)} if the assistanceTechnique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assistance-techniques")
    public ResponseEntity<AssistanceTechnique> updateAssistanceTechnique(@RequestBody AssistanceTechnique assistanceTechnique) throws URISyntaxException {
        log.debug("REST request to update AssistanceTechnique : {}", assistanceTechnique);
        if (assistanceTechnique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AssistanceTechnique result = assistanceTechniqueRepository.save(assistanceTechnique);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, assistanceTechnique.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /assistance-techniques} : get all the assistanceTechniques.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assistanceTechniques in body.
     */
    @GetMapping("/assistance-techniques")
    public List<AssistanceTechnique> getAllAssistanceTechniques() {
        log.debug("REST request to get all AssistanceTechniques");
        return assistanceTechniqueRepository.findAll();
    }

    /**
     * {@code GET  /assistance-techniques/:id} : get the "id" assistanceTechnique.
     *
     * @param id the id of the assistanceTechnique to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assistanceTechnique, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assistance-techniques/{id}")
    public ResponseEntity<AssistanceTechnique> getAssistanceTechnique(@PathVariable Long id) {
        log.debug("REST request to get AssistanceTechnique : {}", id);
        Optional<AssistanceTechnique> assistanceTechnique = assistanceTechniqueRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assistanceTechnique);
    }

    /**
     * {@code DELETE  /assistance-techniques/:id} : delete the "id" assistanceTechnique.
     *
     * @param id the id of the assistanceTechnique to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assistance-techniques/{id}")
    public ResponseEntity<Void> deleteAssistanceTechnique(@PathVariable Long id) {
        log.debug("REST request to delete AssistanceTechnique : {}", id);
        assistanceTechniqueRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
