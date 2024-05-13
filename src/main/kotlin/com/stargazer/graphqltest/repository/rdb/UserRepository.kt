package com.stargazer.graphqltest.repository.rdb

import com.stargazer.graphqltest.entity.rdb.QUserEntity
import com.stargazer.graphqltest.entity.rdb.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository


interface UserRepoCustom {
    fun dslTest(): List<UserEntity>
}

@Repository
interface UserRepository : JpaRepository<UserEntity, Long>, UserRepoCustom {

}

class UserRepositoryImpl : QuerydslRepositorySupport(UserEntity::class.java), UserRepoCustom {
    private val table = QUserEntity.userEntity

    override fun dslTest(): List<UserEntity> {
        val result = from(table)
            .where(table.id.eq(1))
            .fetch()

        return result
    }

}