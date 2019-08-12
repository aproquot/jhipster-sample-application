package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A FormatDate.
 */
@Entity
@Table(name = "format_date")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FormatDate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "format")
    private String format;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public FormatDate libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getFormat() {
        return format;
    }

    public FormatDate format(String format) {
        this.format = format;
        return this;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormatDate)) {
            return false;
        }
        return id != null && id.equals(((FormatDate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FormatDate{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", format='" + getFormat() + "'" +
            "}";
    }
}
