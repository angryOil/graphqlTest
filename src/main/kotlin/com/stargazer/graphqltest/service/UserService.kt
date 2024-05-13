package com.stargazer.graphqltest.service

import com.stargazer.graphqltest.entity.rdb.UserEntity
import com.stargazer.graphqltest.repository.rdb.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun joinUser(name: String): Long {
        return repository.save(UserEntity(id = 0L, name = name)).id ?: 0L
    }

    fun getFirst(): List<UserEntity>{
        return repository.dslTest()
    }
}