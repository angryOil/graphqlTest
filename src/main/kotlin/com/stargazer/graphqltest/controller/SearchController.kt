package com.stargazer.graphqltest.controller

import com.stargazer.graphqltest.entity.nosql.Search
import com.stargazer.graphqltest.service.SearchService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/search")
class SearchController(private val searchService: SearchService) {
    @GetMapping
    fun search(@RequestParam search: String, @RequestParam userId: Long): String {
        return searchService.search(userId, search)
    }

    @GetMapping("/{userId}")
    fun searchInfo(@PathVariable userId: Long): List<Search> {
        return searchService.searchAllByUserId(userId)
    }

}