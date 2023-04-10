package com.PractionBE.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService implements InitializingBean {

    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        tableNames = entityManager.getMetamodel().getEntities().stream()
                .map(entityType -> entityType.getName())
                .collect(Collectors.toList());
    }

    @Transactional
    public void truncateTables(){
        for (String tableName : tableNames){
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }
    }
}
