package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Aer.
 */
@Entity
@Table(name = "aer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Aer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "date_reception")
    private LocalDate dateReception;

    @Column(name = "date_cloture")
    private LocalDate dateCloture;

    @Column(name = "date_reponse")
    private LocalDate dateReponse;

    @Column(name = "action")
    private String action;

    @Column(name = "ao")
    private String ao;

    @Column(name = "montant")
    private String montant;

    @ManyToOne
    @JsonIgnoreProperties("aers")
    private TypeAer typeAer;

    @ManyToOne
    @JsonIgnoreProperties("aers")
    private Statut statut;

    @ManyToOne
    @JsonIgnoreProperties("aers")
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties("aers")
    private Utilisateur utilisateur;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Aer description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateReception() {
        return dateReception;
    }

    public Aer dateReception(LocalDate dateReception) {
        this.dateReception = dateReception;
        return this;
    }

    public void setDateReception(LocalDate dateReception) {
        this.dateReception = dateReception;
    }

    public LocalDate getDateCloture() {
        return dateCloture;
    }

    public Aer dateCloture(LocalDate dateCloture) {
        this.dateCloture = dateCloture;
        return this;
    }

    public void setDateCloture(LocalDate dateCloture) {
        this.dateCloture = dateCloture;
    }

    public LocalDate getDateReponse() {
        return dateReponse;
    }

    public Aer dateReponse(LocalDate dateReponse) {
        this.dateReponse = dateReponse;
        return this;
    }

    public void setDateReponse(LocalDate dateReponse) {
        this.dateReponse = dateReponse;
    }

    public String getAction() {
        return action;
    }

    public Aer action(String action) {
        this.action = action;
        return this;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAo() {
        return ao;
    }

    public Aer ao(String ao) {
        this.ao = ao;
        return this;
    }

    public void setAo(String ao) {
        this.ao = ao;
    }

    public String getMontant() {
        return montant;
    }

    public Aer montant(String montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public TypeAer getTypeAer() {
        return typeAer;
    }

    public Aer typeAer(TypeAer typeAer) {
        this.typeAer = typeAer;
        return this;
    }

    public void setTypeAer(TypeAer typeAer) {
        this.typeAer = typeAer;
    }

    public Statut getStatut() {
        return statut;
    }

    public Aer statut(Statut statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Client getClient() {
        return client;
    }

    public Aer client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Aer utilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Aer)) {
            return false;
        }
        return id != null && id.equals(((Aer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Aer{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", dateReception='" + getDateReception() + "'" +
            ", dateCloture='" + getDateCloture() + "'" +
            ", dateReponse='" + getDateReponse() + "'" +
            ", action='" + getAction() + "'" +
            ", ao='" + getAo() + "'" +
            ", montant='" + getMontant() + "'" +
            "}";
    }
}
