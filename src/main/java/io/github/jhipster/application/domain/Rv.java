package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Rv.
 */
@Entity
@Table(name = "rv")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Rv implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client")
    private String client;

    @Column(name = "agence")
    private String agence;

    @Column(name = "objet")
    private String objet;

    @Column(name = "date_rv")
    private LocalDate dateRv;

    @Column(name = "description")
    private String description;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_modification")
    private LocalDate dateModification;

    @Column(name = "commercial")
    private String commercial;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public Rv client(String client) {
        this.client = client;
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAgence() {
        return agence;
    }

    public Rv agence(String agence) {
        this.agence = agence;
        return this;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getObjet() {
        return objet;
    }

    public Rv objet(String objet) {
        this.objet = objet;
        return this;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public LocalDate getDateRv() {
        return dateRv;
    }

    public Rv dateRv(LocalDate dateRv) {
        this.dateRv = dateRv;
        return this;
    }

    public void setDateRv(LocalDate dateRv) {
        this.dateRv = dateRv;
    }

    public String getDescription() {
        return description;
    }

    public Rv description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Rv dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public Rv dateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
        return this;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }

    public String getCommercial() {
        return commercial;
    }

    public Rv commercial(String commercial) {
        this.commercial = commercial;
        return this;
    }

    public void setCommercial(String commercial) {
        this.commercial = commercial;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rv)) {
            return false;
        }
        return id != null && id.equals(((Rv) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Rv{" +
            "id=" + getId() +
            ", client='" + getClient() + "'" +
            ", agence='" + getAgence() + "'" +
            ", objet='" + getObjet() + "'" +
            ", dateRv='" + getDateRv() + "'" +
            ", description='" + getDescription() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            ", commercial='" + getCommercial() + "'" +
            "}";
    }
}
