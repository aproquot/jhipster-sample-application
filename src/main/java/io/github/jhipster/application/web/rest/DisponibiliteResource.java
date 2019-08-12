package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Disponibilite;
import io.github.jhipster.application.repository.DisponibiliteRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.Disponibilite}.
 */
@RestController
@RequestMapping("/api")
public class DisponibiliteResource {

    private final Logger log = LoggerFactory.getLogger(DisponibiliteResource.class);

    private static final String ENTITY_NAME = "disponibilite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DisponibiliteRepository disponibiliteRepository;

    public DisponibiliteResource(DisponibiliteRepository disponibiliteRepository) {
        this.disponibiliteRepository = disponibiliteRepository;
    }

    /**
     * {@code POST  /disponibilites} : Create a new disponibilite.
     *
     * @param disponibilite the disponibilite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new disponibilite, or with status {@code 400 (Bad Request)} if the disponibilite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/disponibilites")
    public ResponseEntity<Disponibilite> createDisponibilite(@RequestBody Disponibilite disponibilite) throws URISyntaxException {
        log.debug("REST request to save Disponibilite : {}", disponibilite);
        if (disponibilite.getId() != null) {
            throw new BadRequestAlertException("A new disponibilite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Disponibilite result = disponibiliteRepository.save(disponibilite);
        return ResponseEntity.created(new URI("/api/disponibilites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /disponibilites} : Updates an existing disponibilite.
     *
     * @param disponibilite the disponibilite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated disponibilite,
     * or with status {@code 400 (Bad Request)} if the disponibilite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the disponibilite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/disponibilites")
    public ResponseEntity<Disponibilite> updateDisponibilite(@RequestBody Disponibilite disponibilite) throws URISyntaxException {
        log.debug("REST request to update Disponibilite : {}", disponibilite);
        if (disponibilite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Disponibilite result = disponibiliteRepository.save(disponibilite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, disponibilite.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /disponibilites} : get all the disponibilites.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of disponibilites in body.
     */
    @GetMapping("/disponibilites")
    public List<Disponibilite> getAllDisponibilites() {
        log.debug("REST request to get all Disponibilites");
        return disponibiliteRepository.findAll();
    }

    /**
     * {@code GET  /disponibilites/:id} : get the "id" disponibilite.
     *
     * @param id the id of the disponibilite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the disponibilite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/disponibilites/{id}")
    public ResponseEntity<Disponibilite> getDisponibilite(@PathVariable Long id) {
        log.debug("REST request to get Disponibilite : {}", id);
        Optional<Disponibilite> disponibilite = disponibiliteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(disponibilite);
    }

    /**
     * {@code DELETE  /disponibilites/:id} : delete the "id" disponibilite.
     *
     * @param id the id of the disponibilite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/disponibilites/{id}")
    public ResponseEntity<Void> deleteDisponibilite(@PathVariable Long id) {
        log.debug("REST request to delete Disponibilite : {}", id);
        disponibiliteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
