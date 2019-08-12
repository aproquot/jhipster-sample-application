package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Aer;
import io.github.jhipster.application.repository.AerRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.Aer}.
 */
@RestController
@RequestMapping("/api")
public class AerResource {

    private final Logger log = LoggerFactory.getLogger(AerResource.class);

    private static final String ENTITY_NAME = "aer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AerRepository aerRepository;

    public AerResource(AerRepository aerRepository) {
        this.aerRepository = aerRepository;
    }

    /**
     * {@code POST  /aers} : Create a new aer.
     *
     * @param aer the aer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new aer, or with status {@code 400 (Bad Request)} if the aer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/aers")
    public ResponseEntity<Aer> createAer(@RequestBody Aer aer) throws URISyntaxException {
        log.debug("REST request to save Aer : {}", aer);
        if (aer.getId() != null) {
            throw new BadRequestAlertException("A new aer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Aer result = aerRepository.save(aer);
        return ResponseEntity.created(new URI("/api/aers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /aers} : Updates an existing aer.
     *
     * @param aer the aer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aer,
     * or with status {@code 400 (Bad Request)} if the aer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/aers")
    public ResponseEntity<Aer> updateAer(@RequestBody Aer aer) throws URISyntaxException {
        log.debug("REST request to update Aer : {}", aer);
        if (aer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Aer result = aerRepository.save(aer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, aer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /aers} : get all the aers.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aers in body.
     */
    @GetMapping("/aers")
    public List<Aer> getAllAers() {
        log.debug("REST request to get all Aers");
        return aerRepository.findAll();
    }

    /**
     * {@code GET  /aers/:id} : get the "id" aer.
     *
     * @param id the id of the aer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/aers/{id}")
    public ResponseEntity<Aer> getAer(@PathVariable Long id) {
        log.debug("REST request to get Aer : {}", id);
        Optional<Aer> aer = aerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(aer);
    }

    /**
     * {@code DELETE  /aers/:id} : delete the "id" aer.
     *
     * @param id the id of the aer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/aers/{id}")
    public ResponseEntity<Void> deleteAer(@PathVariable Long id) {
        log.debug("REST request to delete Aer : {}", id);
        aerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
