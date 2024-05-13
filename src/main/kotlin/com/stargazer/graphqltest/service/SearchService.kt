package com.stargazer.graphqltest.service

import com.stargazer.graphqltest.entity.nosql.Search
import com.stargazer.graphqltest.repository.nosql.SearchRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SearchService(
    private val searchRepository: SearchRepository
) {
    fun search(userId: Long, keyword: String): String {
        return searchRepository.save(
            Search(
                keyword = keyword,
                userId = userId,
                searchAt = LocalDateTime.now()
            )
        ).id.toString()
    }

    fun searchAllByUserId(userId: Long): List<Search> {
        return searchRepository.searchAllByUserId(userId)
    }
}