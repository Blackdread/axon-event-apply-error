package com.example.axon.common

import com.example.axon.common.RootId.Companion.rootId
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

@JsonDeserialize(using = RootIdDeserializer::class)
@JsonSerialize(using = UuidIdSerializer::class)
class RootId private constructor(override val uuid: UUID) : BaseUuidId(), UuidId {

    companion object {

        private val UUID_ID = UUID.fromString("88888888-1111-2222-3333-444444444444")

        private val ID = RootId(UUID_ID)

        fun rootId(): RootId = ID

        fun rootId(str: String): RootId {
            check(str == ID.uuid.toString()) { "RootId should be '$ID' but got '$str'" }
            return ID
        }

    }

}

class RootIdDeserializer : JsonDeserializer<RootId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): RootId {
        return rootId(p.valueAsString)
    }
}
