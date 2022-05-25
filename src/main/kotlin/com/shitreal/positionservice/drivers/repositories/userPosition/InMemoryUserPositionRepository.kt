package com.shitreal.positionservice.drivers.repositories.userPosition

import com.fasterxml.jackson.module.kotlin.readValue
import com.shitreal.positionservice.adapters.IUserPositionRepository
import com.shitreal.positionservice.drivers.repositories.InMemoryCRUDRepository
import com.shitreal.positionservice.drivers.util.Jsons
import com.shitreal.positionservice.entities.UserPosition

class InMemoryUserPositionRepository: InMemoryCRUDRepository<UserPosition>(),IUserPositionRepository {
    override fun getId(entity: UserPosition): String {
        return entity.userId
    }
    override fun transform(entity: UserPosition): UserPosition {
        val string = Jsons.asJsonString(entity)
        return Jsons.objectMapper.readValue(string)
    }

    override fun findAll(): List<UserPosition> {
        return super.findAll().requireNoNulls()
    }
}