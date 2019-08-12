package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Rv;
import io.github.jhipster.application.service.RvService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.Rv}.
 */
@RestController
@RequestMapping("/api")
public class RvResource {

    private final Logger log = LoggerFactory.getLogger(RvResource.class);

    private static final String ENTITY_NAME = "rv";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RvService rvService;

    public RvResource(RvService rvService) {
        this.rvService = rvService;
    }

    /**
     * {@code POST  /rvs} : Create a new rv.
     *
     * @param rv the rv to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rv, or with status {@code 400 (Bad Request)} if the rv has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rvs")
    public ResponseEntity<Rv> createRv(@RequestBody Rv rv) throws URISyntaxException {
        log.debug("REST request to save Rv : {}", rv);
        if (rv.getId() != null) {
            throw new BadRequestAlertException("A new rv cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Rv result = rvService.save(rv);
        return ResponseEntity.created(new URI("/api/rvs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rvs} : Updates an existing rv.
     *
     * @param rv the rv to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rv,
     * or with status {@code 400 (Bad Request)} if the rv is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rv couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rvs")
    public ResponseEntity<Rv> updateRv(@RequestBody Rv rv) throws URISyntaxException {
        log.debug("REST request to update Rv : {}", rv);
        if (rv.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Rv result = rvService.save(rv);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, rv.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rvs} : get all the rvs.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rvs in body.
     */
    @GetMapping("/rvs")
    public ResponseEntity<List<Rv>> getAllRvs(Pageable pageable) {
        log.debug("REST request to get a page of Rvs");
        Page<Rv> page = rvService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rvs/:id} : get the "id" rv.
     *
     * @param id the id of the rv to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rv, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rvs/{id}")
    public ResponseEntity<Rv> getRv(@PathVariable Long id) {
        log.debug("REST request to get Rv : {}", id);
        Optional<Rv> rv = rvService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rv);
    }

    /**
     * {@code DELETE  /rvs/:id} : delete the "id" rv.
     *
     * @param id the id of the rv to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rvs/{id}")
    public ResponseEntity<Void> deleteRv(@PathVariable Long id) {
        log.debug("REST request to delete Rv : {}", id);
        rvService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
