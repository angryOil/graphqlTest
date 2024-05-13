package com.stargazer.graphqltest.repository.nosql

import com.stargazer.graphqltest.entity.nosql.QSearch
import com.stargazer.graphqltest.entity.nosql.Search
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport

interface SearchRepoCustom {
    fun searchAllByUserId(userId: Long): List<Search>
}

interface SearchRepository : MongoRepository<Search, String>, SearchRepoCustom
//,QuerydslPredicateExecutor<Search>


class SearchRepositoryImpl(@Qualifier("mongoTemplate") op: MongoOperations) : SearchRepoCustom, QuerydslRepositorySupport(op) {
    private val table = QSearch.search

    override fun searchAllByUserId(userId: Long): List<Search> {
        return from(table)
            .where(table.userId.eq(userId))
            .fetch()
    }
}
