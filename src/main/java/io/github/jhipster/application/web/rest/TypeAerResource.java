package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.TypeAer;
import io.github.jhipster.application.repository.TypeAerRepository;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.TypeAer}.
 */
@RestController
@RequestMapping("/api")
public class TypeAerResource {

    private final Logger log = LoggerFactory.getLogger(TypeAerResource.class);

    private static final String ENTITY_NAME = "typeAer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeAerRepository typeAerRepository;

    public TypeAerResource(TypeAerRepository typeAerRepository) {
        this.typeAerRepository = typeAerRepository;
    }

    /**
     * {@code POST  /type-aers} : Create a new typeAer.
     *
     * @param typeAer the typeAer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeAer, or with status {@code 400 (Bad Request)} if the typeAer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-aers")
    public ResponseEntity<TypeAer> createTypeAer(@RequestBody TypeAer typeAer) throws URISyntaxException {
        log.debug("REST request to save TypeAer : {}", typeAer);
        if (typeAer.getId() != null) {
            throw new BadRequestAlertException("A new typeAer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeAer result = typeAerRepository.save(typeAer);
        return ResponseEntity.created(new URI("/api/type-aers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-aers} : Updates an existing typeAer.
     *
     * @param typeAer the typeAer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeAer,
     * or with status {@code 400 (Bad Request)} if the typeAer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeAer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-aers")
    public ResponseEntity<TypeAer> updateTypeAer(@RequestBody TypeAer typeAer) throws URISyntaxException {
        log.debug("REST request to update TypeAer : {}", typeAer);
        if (typeAer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeAer result = typeAerRepository.save(typeAer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, typeAer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-aers} : get all the typeAers.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeAers in body.
     */
    @GetMapping("/type-aers")
    public List<TypeAer> getAllTypeAers() {
        log.debug("REST request to get all TypeAers");
        return typeAerRepository.findAll();
    }

    /**
     * {@code GET  /type-aers/:id} : get the "id" typeAer.
     *
     * @param id the id of the typeAer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeAer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-aers/{id}")
    public ResponseEntity<TypeAer> getTypeAer(@PathVariable Long id) {
        log.debug("REST request to get TypeAer : {}", id);
        Optional<TypeAer> typeAer = typeAerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(typeAer);
    }

    /**
     * {@code DELETE  /type-aers/:id} : delete the "id" typeAer.
     *
     * @param id the id of the typeAer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-aers/{id}")
    public ResponseEntity<Void> deleteTypeAer(@PathVariable Long id) {
        log.debug("REST request to delete TypeAer : {}", id);
        typeAerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
