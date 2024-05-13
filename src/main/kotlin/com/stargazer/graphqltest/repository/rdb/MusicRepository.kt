package com.stargazer.graphqltest.repository.rdb

import com.stargazer.graphqltest.entity.rdb.Music
import com.stargazer.graphqltest.entity.rdb.QMusic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport


interface MusicRepoCustom {
    fun searchByTitle(title: String): List<Music>
    fun searchByArtist(artist: String): List<Music>
}

interface MusicRepository : JpaRepository<Music, Long>, MusicRepoCustom {
}

class MusicRepositoryImpl : QuerydslRepositorySupport(Music::class.java), MusicRepoCustom {
    private val table = QMusic.music
    override fun searchByTitle(title: String): List<Music> {
        val result = from(table)
            .where(table.title.eq(title))
            .fetch()
        return result
    }

    override fun searchByArtist(artist: String): List<Music> {
        val result = from(table)
            .where(table.artist.eq(artist))
            .fetch()
        return result
    }

}