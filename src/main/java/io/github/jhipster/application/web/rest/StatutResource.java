package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Statut;
import io.github.jhipster.application.repository.StatutRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.Statut}.
 */
@RestController
@RequestMapping("/api")
public class StatutResource {

    private final Logger log = LoggerFactory.getLogger(StatutResource.class);

    private static final String ENTITY_NAME = "statut";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StatutRepository statutRepository;

    public StatutResource(StatutRepository statutRepository) {
        this.statutRepository = statutRepository;
    }

    /**
     * {@code POST  /statuts} : Create a new statut.
     *
     * @param statut the statut to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new statut, or with status {@code 400 (Bad Request)} if the statut has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/statuts")
    public ResponseEntity<Statut> createStatut(@RequestBody Statut statut) throws URISyntaxException {
        log.debug("REST request to save Statut : {}", statut);
        if (statut.getId() != null) {
            throw new BadRequestAlertException("A new statut cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Statut result = statutRepository.save(statut);
        return ResponseEntity.created(new URI("/api/statuts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /statuts} : Updates an existing statut.
     *
     * @param statut the statut to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated statut,
     * or with status {@code 400 (Bad Request)} if the statut is not valid,
     * or with status {@code 500 (Internal Server Error)} if the statut couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/statuts")
    public ResponseEntity<Statut> updateStatut(@RequestBody Statut statut) throws URISyntaxException {
        log.debug("REST request to update Statut : {}", statut);
        if (statut.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Statut result = statutRepository.save(statut);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, statut.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /statuts} : get all the statuts.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statuts in body.
     */
    @GetMapping("/statuts")
    public List<Statut> getAllStatuts() {
        log.debug("REST request to get all Statuts");
        return statutRepository.findAll();
    }

    /**
     * {@code GET  /statuts/:id} : get the "id" statut.
     *
     * @param id the id of the statut to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statut, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statuts/{id}")
    public ResponseEntity<Statut> getStatut(@PathVariable Long id) {
        log.debug("REST request to get Statut : {}", id);
        Optional<Statut> statut = statutRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(statut);
    }

    /**
     * {@code DELETE  /statuts/:id} : delete the "id" statut.
     *
     * @param id the id of the statut to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/statuts/{id}")
    public ResponseEntity<Void> deleteStatut(@PathVariable Long id) {
        log.debug("REST request to delete Statut : {}", id);
        statutRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
