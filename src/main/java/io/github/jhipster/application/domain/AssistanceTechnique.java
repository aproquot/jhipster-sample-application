package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A AssistanceTechnique.
 */
@Entity
@Table(name = "assistance_technique")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AssistanceTechnique implements Serializable {

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

    @Column(name = "code_profil")
    private String codeProfil;

    @Column(name = "tjm")
    private String tjm;

    @Column(name = "date_demarrage")
    private LocalDate dateDemarrage;

    @ManyToOne
    @JsonIgnoreProperties("assistanceTechniques")
    private Statut statut;

    @ManyToOne
    @JsonIgnoreProperties("assistanceTechniques")
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties("assistanceTechniques")
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

    public AssistanceTechnique description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateReception() {
        return dateReception;
    }

    public AssistanceTechnique dateReception(LocalDate dateReception) {
        this.dateReception = dateReception;
        return this;
    }

    public void setDateReception(LocalDate dateReception) {
        this.dateReception = dateReception;
    }

    public LocalDate getDateCloture() {
        return dateCloture;
    }

    public AssistanceTechnique dateCloture(LocalDate dateCloture) {
        this.dateCloture = dateCloture;
        return this;
    }

    public void setDateCloture(LocalDate dateCloture) {
        this.dateCloture = dateCloture;
    }

    public LocalDate getDateReponse() {
        return dateReponse;
    }

    public AssistanceTechnique dateReponse(LocalDate dateReponse) {
        this.dateReponse = dateReponse;
        return this;
    }

    public void setDateReponse(LocalDate dateReponse) {
        this.dateReponse = dateReponse;
    }

    public String getAction() {
        return action;
    }

    public AssistanceTechnique action(String action) {
        this.action = action;
        return this;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAo() {
        return ao;
    }

    public AssistanceTechnique ao(String ao) {
        this.ao = ao;
        return this;
    }

    public void setAo(String ao) {
        this.ao = ao;
    }

    public String getCodeProfil() {
        return codeProfil;
    }

    public AssistanceTechnique codeProfil(String codeProfil) {
        this.codeProfil = codeProfil;
        return this;
    }

    public void setCodeProfil(String codeProfil) {
        this.codeProfil = codeProfil;
    }

    public String getTjm() {
        return tjm;
    }

    public AssistanceTechnique tjm(String tjm) {
        this.tjm = tjm;
        return this;
    }

    public void setTjm(String tjm) {
        this.tjm = tjm;
    }

    public LocalDate getDateDemarrage() {
        return dateDemarrage;
    }

    public AssistanceTechnique dateDemarrage(LocalDate dateDemarrage) {
        this.dateDemarrage = dateDemarrage;
        return this;
    }

    public void setDateDemarrage(LocalDate dateDemarrage) {
        this.dateDemarrage = dateDemarrage;
    }

    public Statut getStatut() {
        return statut;
    }

    public AssistanceTechnique statut(Statut statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Client getClient() {
        return client;
    }

    public AssistanceTechnique client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public AssistanceTechnique utilisateur(Utilisateur utilisateur) {
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
        if (!(o instanceof AssistanceTechnique)) {
            return false;
        }
        return id != null && id.equals(((AssistanceTechnique) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AssistanceTechnique{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", dateReception='" + getDateReception() + "'" +
            ", dateCloture='" + getDateCloture() + "'" +
            ", dateReponse='" + getDateReponse() + "'" +
            ", action='" + getAction() + "'" +
            ", ao='" + getAo() + "'" +
            ", codeProfil='" + getCodeProfil() + "'" +
            ", tjm='" + getTjm() + "'" +
            ", dateDemarrage='" + getDateDemarrage() + "'" +
            "}";
    }
}
