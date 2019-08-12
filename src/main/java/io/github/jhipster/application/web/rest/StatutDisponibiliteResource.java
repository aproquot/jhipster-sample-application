package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.StatutDisponibilite;
import io.github.jhipster.application.repository.StatutDisponibiliteRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.StatutDisponibilite}.
 */
@RestController
@RequestMapping("/api")
public class StatutDisponibiliteResource {

    private final Logger log = LoggerFactory.getLogger(StatutDisponibiliteResource.class);

    private static final String ENTITY_NAME = "statutDisponibilite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StatutDisponibiliteRepository statutDisponibiliteRepository;

    public StatutDisponibiliteResource(StatutDisponibiliteRepository statutDisponibiliteRepository) {
        this.statutDisponibiliteRepository = statutDisponibiliteRepository;
    }

    /**
     * {@code POST  /statut-disponibilites} : Create a new statutDisponibilite.
     *
     * @param statutDisponibilite the statutDisponibilite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new statutDisponibilite, or with status {@code 400 (Bad Request)} if the statutDisponibilite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/statut-disponibilites")
    public ResponseEntity<StatutDisponibilite> createStatutDisponibilite(@RequestBody StatutDisponibilite statutDisponibilite) throws URISyntaxException {
        log.debug("REST request to save StatutDisponibilite : {}", statutDisponibilite);
        if (statutDisponibilite.getId() != null) {
            throw new BadRequestAlertException("A new statutDisponibilite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatutDisponibilite result = statutDisponibiliteRepository.save(statutDisponibilite);
        return ResponseEntity.created(new URI("/api/statut-disponibilites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /statut-disponibilites} : Updates an existing statutDisponibilite.
     *
     * @param statutDisponibilite the statutDisponibilite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated statutDisponibilite,
     * or with status {@code 400 (Bad Request)} if the statutDisponibilite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the statutDisponibilite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/statut-disponibilites")
    public ResponseEntity<StatutDisponibilite> updateStatutDisponibilite(@RequestBody StatutDisponibilite statutDisponibilite) throws URISyntaxException {
        log.debug("REST request to update StatutDisponibilite : {}", statutDisponibilite);
        if (statutDisponibilite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatutDisponibilite result = statutDisponibiliteRepository.save(statutDisponibilite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, statutDisponibilite.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /statut-disponibilites} : get all the statutDisponibilites.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statutDisponibilites in body.
     */
    @GetMapping("/statut-disponibilites")
    public List<StatutDisponibilite> getAllStatutDisponibilites() {
        log.debug("REST request to get all StatutDisponibilites");
        return statutDisponibiliteRepository.findAll();
    }

    /**
     * {@code GET  /statut-disponibilites/:id} : get the "id" statutDisponibilite.
     *
     * @param id the id of the statutDisponibilite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statutDisponibilite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statut-disponibilites/{id}")
    public ResponseEntity<StatutDisponibilite> getStatutDisponibilite(@PathVariable Long id) {
        log.debug("REST request to get StatutDisponibilite : {}", id);
        Optional<StatutDisponibilite> statutDisponibilite = statutDisponibiliteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(statutDisponibilite);
    }

    /**
     * {@code DELETE  /statut-disponibilites/:id} : delete the "id" statutDisponibilite.
     *
     * @param id the id of the statutDisponibilite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/statut-disponibilites/{id}")
    public ResponseEntity<Void> deleteStatutDisponibilite(@PathVariable Long id) {
        log.debug("REST request to delete StatutDisponibilite : {}", id);
        statutDisponibiliteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
