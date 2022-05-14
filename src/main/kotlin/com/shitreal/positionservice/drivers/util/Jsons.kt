package com.shitreal.positionservice.drivers.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.annotation.JsonInclude
import java.lang.RuntimeException
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.lang.Exception

object Jsons {
    fun asJsonString(obj: Any?): String {
        return try {
            val mapper = objectMapper
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
            mapper.writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    val objectMapper: ObjectMapper
        get() = ObjectMapper().registerModule(ParameterNamesModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(Jdk8Module())
            .registerModule(KotlinModule.Builder().build())
            .registerModule(JavaTimeModule())
}