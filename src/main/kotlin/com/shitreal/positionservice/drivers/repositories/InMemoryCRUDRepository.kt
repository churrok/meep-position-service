package com.shitreal.positionservice.drivers.repositories

import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

abstract class InMemoryCRUDRepository<Entity> {
    val database: MutableMap<String, Entity> = HashMap()

    protected abstract fun getId(entity: Entity): String
    protected abstract fun transform(entity: Entity): Entity

    open fun saveWithKey(key: String, entity: Entity): Entity {
        database[key] = transform(entity)
        return entity
    }

    fun save(entity: Entity): Entity {
        database[getId(entity)] = transform(entity)
        return entity
    }

    open fun findAll(): List<Entity?> {
        return ArrayList(database.values)
    }

    fun findById(identifier: String): Entity? {
        return database.values.firstOrNull { getId(it).equals(identifier) }
    }

    open fun saveAll(resources: List<Entity>): List<Entity> {
        return resources.stream().map { `object`: Entity -> save(`object`) }.collect(Collectors.toList())
    }

    fun deleteAll(resources: List<Entity>) {
        resources.forEach(Consumer { resource: Entity -> delete(resource) })
    }

    fun delete(resource: Entity) {
        database.remove(getId(resource))
    }
}