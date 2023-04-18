package com.example.axon.common

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.util.*

interface UuidId : Comparable<UuidId> {
    val uuid: UUID

    override fun compareTo(other: UuidId): Int = uuid.compareTo(other.uuid)
}

class UuidIdSerializer : JsonSerializer<UuidId>() {
    override fun serialize(value: UuidId, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.uuid.toString())
    }
}