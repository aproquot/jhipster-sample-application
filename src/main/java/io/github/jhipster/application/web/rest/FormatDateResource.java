package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.FormatDate;
import io.github.jhipster.application.service.FormatDateService;
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
 * REST controller for managing {@link io.github.jhipster.application.domain.FormatDate}.
 */
@RestController
@RequestMapping("/api")
public class FormatDateResource {

    private final Logger log = LoggerFactory.getLogger(FormatDateResource.class);

    private static final String ENTITY_NAME = "formatDate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormatDateService formatDateService;

    public FormatDateResource(FormatDateService formatDateService) {
        this.formatDateService = formatDateService;
    }

    /**
     * {@code POST  /format-dates} : Create a new formatDate.
     *
     * @param formatDate the formatDate to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formatDate, or with status {@code 400 (Bad Request)} if the formatDate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/format-dates")
    public ResponseEntity<FormatDate> createFormatDate(@RequestBody FormatDate formatDate) throws URISyntaxException {
        log.debug("REST request to save FormatDate : {}", formatDate);
        if (formatDate.getId() != null) {
            throw new BadRequestAlertException("A new formatDate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormatDate result = formatDateService.save(formatDate);
        return ResponseEntity.created(new URI("/api/format-dates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /format-dates} : Updates an existing formatDate.
     *
     * @param formatDate the formatDate to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formatDate,
     * or with status {@code 400 (Bad Request)} if the formatDate is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formatDate couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/format-dates")
    public ResponseEntity<FormatDate> updateFormatDate(@RequestBody FormatDate formatDate) throws URISyntaxException {
        log.debug("REST request to update FormatDate : {}", formatDate);
        if (formatDate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormatDate result = formatDateService.save(formatDate);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, formatDate.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /format-dates} : get all the formatDates.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formatDates in body.
     */
    @GetMapping("/format-dates")
    public List<FormatDate> getAllFormatDates() {
        log.debug("REST request to get all FormatDates");
        return formatDateService.findAll();
    }

    /**
     * {@code GET  /format-dates/:id} : get the "id" formatDate.
     *
     * @param id the id of the formatDate to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formatDate, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/format-dates/{id}")
    public ResponseEntity<FormatDate> getFormatDate(@PathVariable Long id) {
        log.debug("REST request to get FormatDate : {}", id);
        Optional<FormatDate> formatDate = formatDateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formatDate);
    }

    /**
     * {@code DELETE  /format-dates/:id} : delete the "id" formatDate.
     *
     * @param id the id of the formatDate to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/format-dates/{id}")
    public ResponseEntity<Void> deleteFormatDate(@PathVariable Long id) {
        log.debug("REST request to delete FormatDate : {}", id);
        formatDateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
