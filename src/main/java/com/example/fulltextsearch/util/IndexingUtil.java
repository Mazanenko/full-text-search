package com.example.fulltextsearch.util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Slf4j
public class IndexingUtil {
    @PersistenceContext
    private EntityManager entityManager;

    @EventListener
    @Transactional
    public void initialIndexingOnStartUp(ContextRefreshedEvent contextRefreshedEvent) throws InterruptedException {
        log.info("starting initial indexing entities to elasticsearch");
        SearchSession searchSession = Search.session(entityManager);
        MassIndexer indexer = searchSession.massIndexer().threadsToLoadObjects(5);
        indexer.startAndWait();
    }
}
