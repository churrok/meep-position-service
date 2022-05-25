package com.shitreal.positionservice.tdd

import com.shitreal.positionservice.drivers.repositories.userPosition.InMemoryUserPositionRepository
import com.shitreal.positionservice.entities.UserPosition
import com.shitreal.positionservice.interactors.RefreshUserPosition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RefreshUserPositionTest {
    private val userPositionRepository = InMemoryUserPositionRepository()
    private val refreshUserPosition = RefreshUserPosition(userPositionRepository)
    @Test
    fun calculateDistanceBetweenTwoCoordinates(){
        val user1 = UserPosition(userId = "id1", latitude = "-34.52626693083747",  longitude = "-58.70168721525687")
        val user2 = UserPosition(userId = "id2", latitude = "-34.53317000356777",  longitude = "-58.70495951025246")
        assertThat(refreshUserPosition.distanceBetween(user1,user2)).isGreaterThan(0)
    }

}