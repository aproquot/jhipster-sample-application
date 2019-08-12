package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Statut.
 */
@Entity
@Table(name = "statut")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Statut implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "ordonnance")
    private Integer ordonnance;

    @OneToMany(mappedBy = "statut")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Aer> aers = new HashSet<>();

    @OneToMany(mappedBy = "statut")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AssistanceTechnique> assistanceTechniques = new HashSet<>();

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

    public Statut libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getOrdonnance() {
        return ordonnance;
    }

    public Statut ordonnance(Integer ordonnance) {
        this.ordonnance = ordonnance;
        return this;
    }

    public void setOrdonnance(Integer ordonnance) {
        this.ordonnance = ordonnance;
    }

    public Set<Aer> getAers() {
        return aers;
    }

    public Statut aers(Set<Aer> aers) {
        this.aers = aers;
        return this;
    }

    public Statut addAer(Aer aer) {
        this.aers.add(aer);
        aer.setStatut(this);
        return this;
    }

    public Statut removeAer(Aer aer) {
        this.aers.remove(aer);
        aer.setStatut(null);
        return this;
    }

    public void setAers(Set<Aer> aers) {
        this.aers = aers;
    }

    public Set<AssistanceTechnique> getAssistanceTechniques() {
        return assistanceTechniques;
    }

    public Statut assistanceTechniques(Set<AssistanceTechnique> assistanceTechniques) {
        this.assistanceTechniques = assistanceTechniques;
        return this;
    }

    public Statut addAssistanceTechnique(AssistanceTechnique assistanceTechnique) {
        this.assistanceTechniques.add(assistanceTechnique);
        assistanceTechnique.setStatut(this);
        return this;
    }

    public Statut removeAssistanceTechnique(AssistanceTechnique assistanceTechnique) {
        this.assistanceTechniques.remove(assistanceTechnique);
        assistanceTechnique.setStatut(null);
        return this;
    }

    public void setAssistanceTechniques(Set<AssistanceTechnique> assistanceTechniques) {
        this.assistanceTechniques = assistanceTechniques;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Statut)) {
            return false;
        }
        return id != null && id.equals(((Statut) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Statut{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", ordonnance=" + getOrdonnance() +
            "}";
    }
}
