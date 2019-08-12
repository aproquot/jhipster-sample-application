package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Aer> aers = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AssistanceTechnique> assistanceTechniques = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("clients")
    private Agence agence;

    @ManyToOne
    @JsonIgnoreProperties("clients")
    private Groupe groupe;

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

    public Client libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Aer> getAers() {
        return aers;
    }

    public Client aers(Set<Aer> aers) {
        this.aers = aers;
        return this;
    }

    public Client addAer(Aer aer) {
        this.aers.add(aer);
        aer.setClient(this);
        return this;
    }

    public Client removeAer(Aer aer) {
        this.aers.remove(aer);
        aer.setClient(null);
        return this;
    }

    public void setAers(Set<Aer> aers) {
        this.aers = aers;
    }

    public Set<AssistanceTechnique> getAssistanceTechniques() {
        return assistanceTechniques;
    }

    public Client assistanceTechniques(Set<AssistanceTechnique> assistanceTechniques) {
        this.assistanceTechniques = assistanceTechniques;
        return this;
    }

    public Client addAssistanceTechnique(AssistanceTechnique assistanceTechnique) {
        this.assistanceTechniques.add(assistanceTechnique);
        assistanceTechnique.setClient(this);
        return this;
    }

    public Client removeAssistanceTechnique(AssistanceTechnique assistanceTechnique) {
        this.assistanceTechniques.remove(assistanceTechnique);
        assistanceTechnique.setClient(null);
        return this;
    }

    public void setAssistanceTechniques(Set<AssistanceTechnique> assistanceTechniques) {
        this.assistanceTechniques = assistanceTechniques;
    }

    public Agence getAgence() {
        return agence;
    }

    public Client agence(Agence agence) {
        this.agence = agence;
        return this;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public Client groupe(Groupe groupe) {
        this.groupe = groupe;
        return this;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
