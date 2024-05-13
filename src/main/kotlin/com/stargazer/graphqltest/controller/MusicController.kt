package com.stargazer.graphqltest.controller

import com.stargazer.graphqltest.entity.rdb.Music
import com.stargazer.graphqltest.service.MusicService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/music")
class MusicController(
    private val musicService: MusicService,
) {

    @QueryMapping
    fun music(@Argument title: String): List<Music> {
        return musicService.findMusic(title)
    }

    @MutationMapping
    fun createMusic(@Argument title: String, @Argument artist: String): Long {
        return musicService.registerMusic(title, artist)
    }
}