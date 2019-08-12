package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.FormatDateService;
import io.github.jhipster.application.domain.FormatDate;
import io.github.jhipster.application.repository.FormatDateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FormatDate}.
 */
@Service
@Transactional
public class FormatDateServiceImpl implements FormatDateService {

    private final Logger log = LoggerFactory.getLogger(FormatDateServiceImpl.class);

    private final FormatDateRepository formatDateRepository;

    public FormatDateServiceImpl(FormatDateRepository formatDateRepository) {
        this.formatDateRepository = formatDateRepository;
    }

    /**
     * Save a formatDate.
     *
     * @param formatDate the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FormatDate save(FormatDate formatDate) {
        log.debug("Request to save FormatDate : {}", formatDate);
        return formatDateRepository.save(formatDate);
    }

    /**
     * Get all the formatDates.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FormatDate> findAll() {
        log.debug("Request to get all FormatDates");
        return formatDateRepository.findAll();
    }


    /**
     * Get one formatDate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FormatDate> findOne(Long id) {
        log.debug("Request to get FormatDate : {}", id);
        return formatDateRepository.findById(id);
    }

    /**
     * Delete the formatDate by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormatDate : {}", id);
        formatDateRepository.deleteById(id);
    }
}
