package com.stargazer.graphqltest.entity.nosql

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.bson.types.ObjectId
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
@Entity
class Search(
    @Id val id: ObjectId = ObjectId.get(),
    val keyword: String,
    val userId: Long,
    @CreationTimestamp
    val searchAt: LocalDateTime,
) {
}