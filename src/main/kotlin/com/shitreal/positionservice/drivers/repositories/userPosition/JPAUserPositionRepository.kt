package com.shitreal.positionservice.drivers.repositories.userPosition

import com.shitreal.positionservice.adapters.IUserPositionRepository
import com.shitreal.positionservice.entities.UserPosition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface UserPositionRepository : CrudRepository<UserPosition, String> {
}

@Repository
class JPAUserPositionRepository(
    @Autowired
    private val userPositionRepository: UserPositionRepository
): IUserPositionRepository {
    override fun findAll(): List<UserPosition> {
        return this.userPositionRepository.findAll().toList()
    }
    override fun save(entity: UserPosition): UserPosition {
        return this.userPositionRepository.save(entity)
    }
}