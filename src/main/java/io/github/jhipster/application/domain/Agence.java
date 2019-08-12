package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Agence.
 */
@Entity
@Table(name = "agence")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Agence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "agence")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "agence")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Disponibilite> disponibilites = new HashSet<>();

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

    public Agence libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Agence clients(Set<Client> clients) {
        this.clients = clients;
        return this;
    }

    public Agence addClient(Client client) {
        this.clients.add(client);
        client.setAgence(this);
        return this;
    }

    public Agence removeClient(Client client) {
        this.clients.remove(client);
        client.setAgence(null);
        return this;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Disponibilite> getDisponibilites() {
        return disponibilites;
    }

    public Agence disponibilites(Set<Disponibilite> disponibilites) {
        this.disponibilites = disponibilites;
        return this;
    }

    public Agence addDisponibilite(Disponibilite disponibilite) {
        this.disponibilites.add(disponibilite);
        disponibilite.setAgence(this);
        return this;
    }

    public Agence removeDisponibilite(Disponibilite disponibilite) {
        this.disponibilites.remove(disponibilite);
        disponibilite.setAgence(null);
        return this;
    }

    public void setDisponibilites(Set<Disponibilite> disponibilites) {
        this.disponibilites = disponibilites;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agence)) {
            return false;
        }
        return id != null && id.equals(((Agence) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Agence{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
