package com.stargazer.graphqltest.entity

import jakarta.persistence.*

@Entity
@Table(name = "st_user")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String
)
