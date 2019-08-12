package io.github.jhipster.application.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, io.github.jhipster.application.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, io.github.jhipster.application.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, io.github.jhipster.application.domain.User.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Authority.class.getName());
            createCache(cm, io.github.jhipster.application.domain.User.class.getName() + ".authorities");
            createCache(cm, io.github.jhipster.application.domain.Agence.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Agence.class.getName() + ".clients");
            createCache(cm, io.github.jhipster.application.domain.Agence.class.getName() + ".disponibilites");
            createCache(cm, io.github.jhipster.application.domain.TypeAer.class.getName());
            createCache(cm, io.github.jhipster.application.domain.TypeAer.class.getName() + ".aers");
            createCache(cm, io.github.jhipster.application.domain.StatutDisponibilite.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Statut.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Statut.class.getName() + ".aers");
            createCache(cm, io.github.jhipster.application.domain.Statut.class.getName() + ".assistanceTechniques");
            createCache(cm, io.github.jhipster.application.domain.Groupe.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Groupe.class.getName() + ".clients");
            createCache(cm, io.github.jhipster.application.domain.Client.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Client.class.getName() + ".aers");
            createCache(cm, io.github.jhipster.application.domain.Client.class.getName() + ".assistanceTechniques");
            createCache(cm, io.github.jhipster.application.domain.Aer.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Disponibilite.class.getName());
            createCache(cm, io.github.jhipster.application.domain.AssistanceTechnique.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Utilisateur.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Utilisateur.class.getName() + ".aers");
            createCache(cm, io.github.jhipster.application.domain.Utilisateur.class.getName() + ".assistanceTechniques");
            createCache(cm, io.github.jhipster.application.domain.Role.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Role.class.getName() + ".utilisateurs");
            createCache(cm, io.github.jhipster.application.domain.FormatDate.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Rv.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }
}
