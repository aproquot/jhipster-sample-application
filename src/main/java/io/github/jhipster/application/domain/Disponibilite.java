package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Disponibilite.
 */
@Entity
@Table(name = "disponibilite")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Disponibilite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "competence")
    private String competence;

    @Column(name = "logement")
    private String logement;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "affaire_en_vue")
    private String affaireEnVue;

    @Column(name = "ic_domicile")
    private Boolean icDomicile;

    @ManyToOne
    @JsonIgnoreProperties("disponibilites")
    private Agence agence;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Disponibilite nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Disponibilite prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDate() {
        return date;
    }

    public Disponibilite date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCompetence() {
        return competence;
    }

    public Disponibilite competence(String competence) {
        this.competence = competence;
        return this;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getLogement() {
        return logement;
    }

    public Disponibilite logement(String logement) {
        this.logement = logement;
        return this;
    }

    public void setLogement(String logement) {
        this.logement = logement;
    }

    public String getOccupation() {
        return occupation;
    }

    public Disponibilite occupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAffaireEnVue() {
        return affaireEnVue;
    }

    public Disponibilite affaireEnVue(String affaireEnVue) {
        this.affaireEnVue = affaireEnVue;
        return this;
    }

    public void setAffaireEnVue(String affaireEnVue) {
        this.affaireEnVue = affaireEnVue;
    }

    public Boolean isIcDomicile() {
        return icDomicile;
    }

    public Disponibilite icDomicile(Boolean icDomicile) {
        this.icDomicile = icDomicile;
        return this;
    }

    public void setIcDomicile(Boolean icDomicile) {
        this.icDomicile = icDomicile;
    }

    public Agence getAgence() {
        return agence;
    }

    public Disponibilite agence(Agence agence) {
        this.agence = agence;
        return this;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Disponibilite)) {
            return false;
        }
        return id != null && id.equals(((Disponibilite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Disponibilite{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", date='" + getDate() + "'" +
            ", competence='" + getCompetence() + "'" +
            ", logement='" + getLogement() + "'" +
            ", occupation='" + getOccupation() + "'" +
            ", affaireEnVue='" + getAffaireEnVue() + "'" +
            ", icDomicile='" + isIcDomicile() + "'" +
            "}";
    }
}
