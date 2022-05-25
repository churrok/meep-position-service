package com.shitreal.positionservice.drivers

import com.shitreal.positionservice.adapters.IUserPositionRepository
import com.shitreal.positionservice.interactors.RefreshUserPosition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {
    @Bean
    fun refreshUserPosition(userPositionsRepository: IUserPositionRepository): RefreshUserPosition{
        return RefreshUserPosition(userPositionsRepository)
    }
}