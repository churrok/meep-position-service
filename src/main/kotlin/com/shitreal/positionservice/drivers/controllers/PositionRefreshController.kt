package com.shitreal.positionservice.drivers.controllers

import com.shitreal.positionservice.entities.UserPosition
import com.shitreal.positionservice.interactors.RefreshUserPosition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user/position")
class PositionRefreshController(
    @Autowired
    private val refreshUserPosition: RefreshUserPosition
) {
    @PutMapping("/refresh")
    fun refreshUserPosition(@RequestBody userPosition: UserPosition): List<UserPosition>{
        return this.refreshUserPosition.refresh(userId = userPosition.userId, latitude = userPosition.latitude, longitude = userPosition.longitude)
    }
}