package com.shitreal.positionservice.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_position")
data class UserPosition(
    @Id
    val userId: String,
    val latitude: String,
    val longitude: String
)