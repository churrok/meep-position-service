package com.shitreal.positionservice.interactors

import com.shitreal.positionservice.adapters.IUserPositionRepository
import com.shitreal.positionservice.entities.UserPosition
import kotlin.math.*

class RefreshUserPosition(private val userPositionRepository: IUserPositionRepository) {

    fun refresh(userId: String, latitude: String, longitude: String): List<UserPosition>{
        val userPosition = UserPosition(userId = userId, latitude = latitude, longitude = longitude)
        val ret = mutableListOf<UserPosition>()
        val allUserPosition = userPositionRepository.findAll()
        allUserPosition.forEach {
            if(distanceBetween(pointA = userPosition, pointB = it) < 50) ret.add(it)
        }
        userPositionRepository.save(userPosition)
        return ret
    }

    fun distanceBetween(pointA: UserPosition, pointB: UserPosition): Int {

        val globeRadius = 6371 // Radius of the earth

        val lat1 = pointA.latitude.toDouble()
        val lat2 = pointB.latitude.toDouble()

        val lon1 = pointA.longitude.toDouble()
        val lon2 = pointB.longitude.toDouble()

        val el1 = 0.0
        val el2 = 0.0

        val latDistance = Math.toRadians(lat2-lat1)
        val lonDistance = Math.toRadians(lon2 - lon1)

        val a = (sin(latDistance / 2) * sin(latDistance / 2)
                + (cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2))
                * sin(lonDistance / 2) * sin(lonDistance / 2)))
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        var distance = globeRadius * c * 1000 // convert to meters

        val height: Double = el1 - el2

        distance = distance.pow(2.0) + height.pow(2.0)

        return sqrt(distance).toInt()
    }
}