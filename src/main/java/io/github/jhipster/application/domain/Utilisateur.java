package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Utilisateur.
 */
@Entity
@Table(name = "utilisateur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Aer> aers = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AssistanceTechnique> assistanceTechniques = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("utilisateurs")
    private Role role;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public Utilisateur login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Aer> getAers() {
        return aers;
    }

    public Utilisateur aers(Set<Aer> aers) {
        this.aers = aers;
        return this;
    }

    public Utilisateur addAer(Aer aer) {
        this.aers.add(aer);
        aer.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeAer(Aer aer) {
        this.aers.remove(aer);
        aer.setUtilisateur(null);
        return this;
    }

    public void setAers(Set<Aer> aers) {
        this.aers = aers;
    }

    public Set<AssistanceTechnique> getAssistanceTechniques() {
        return assistanceTechniques;
    }

    public Utilisateur assistanceTechniques(Set<AssistanceTechnique> assistanceTechniques) {
        this.assistanceTechniques = assistanceTechniques;
        return this;
    }

    public Utilisateur addAssistanceTechnique(AssistanceTechnique assistanceTechnique) {
        this.assistanceTechniques.add(assistanceTechnique);
        assistanceTechnique.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeAssistanceTechnique(AssistanceTechnique assistanceTechnique) {
        this.assistanceTechniques.remove(assistanceTechnique);
        assistanceTechnique.setUtilisateur(null);
        return this;
    }

    public void setAssistanceTechniques(Set<AssistanceTechnique> assistanceTechniques) {
        this.assistanceTechniques = assistanceTechniques;
    }

    public Role getRole() {
        return role;
    }

    public Utilisateur role(Role role) {
        this.role = role;
        return this;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Utilisateur)) {
            return false;
        }
        return id != null && id.equals(((Utilisateur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
            "id=" + getId() +
            ", login='" + getLogin() + "'" +
            "}";
    }
}
