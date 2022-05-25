package com.shitreal.positionservice.adapters

import com.shitreal.positionservice.entities.UserPosition

interface IUserPositionRepository {
    fun findAll(): List<UserPosition>
    fun save(entity: UserPosition): UserPosition
}