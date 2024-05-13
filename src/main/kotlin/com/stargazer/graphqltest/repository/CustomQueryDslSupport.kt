package com.stargazer.graphqltest.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
abstract class CustomQueryDslSupport(domainClass: Class<*>) : QuerydslRepositorySupport(domainClass) {
    @PersistenceContext
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }
}