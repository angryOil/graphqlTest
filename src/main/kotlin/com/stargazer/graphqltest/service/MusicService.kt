package com.stargazer.graphqltest.service

import com.stargazer.graphqltest.entity.Music
import com.stargazer.graphqltest.repository.MusicRepository
import org.springframework.stereotype.Service

@Service
class MusicService(
    private val musicRepository: MusicRepository
) {
    fun registerMusic(title: String, artist: String): Long {
        return musicRepository.save(Music(id = 0L, title = title, artist = artist)).id
    }

    fun findMusic(title: String): List<Music> {
        return musicRepository.searchByTitle(title)
    }
}