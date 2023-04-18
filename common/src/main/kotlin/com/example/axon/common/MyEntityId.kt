package com.example.axon.common

import com.example.axon.common.MyEntityId.Companion.parseMyEntityId
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

@JsonDeserialize(using = MyEntityIdDeserializer::class)
@JsonSerialize(using = UuidIdSerializer::class)
class MyEntityId private constructor(override val uuid: UUID) : BaseUuidId(), UuidId {

    private constructor(identifier: String) : this(UUID.fromString(identifier))

    companion object {
        @JvmStatic
        private val ID_MAP: EnumMap<MyEntityType, MyEntityId> = EnumMap(
            mapOf(
                MyEntityType.OTHER1 to MyEntityId("11111111-1111-2222-3333-000000000002"),
                MyEntityType.OTHER2 to MyEntityId("11111111-1111-2222-3333-000000000003"),
                MyEntityType.OTHER3 to MyEntityId("11111111-1111-2222-3333-000000000004"),
            )
        )

        @JvmStatic
        private val UUID_MAP: Map<UUID, MyEntityId> = ID_MAP.mapKeys { it.value.uuid }

        fun myEntityId(myEntityType: MyEntityType): MyEntityId = ID_MAP.getValue(myEntityType)

        fun myEntityId(myEntityId: UUID): MyEntityId = UUID_MAP.getValue(myEntityId)

        fun checkMyEntityId(myEntityId: MyEntityId, myEntityType: MyEntityType) {
            val expected = myEntityId(myEntityType)
            check(expected == myEntityId) { "MyEntityId does not match: $myEntityId != $expected" }
        }

        fun parseMyEntityId(str: String): MyEntityId {
            return ID_MAP.entries.firstOrNull { it.value.uuid.toString() == str }?.value
                ?: throw IllegalStateException("$str not found for ${MyEntityId::class.java.simpleName}")
        }
    }

}

class MyEntityIdDeserializer : JsonDeserializer<MyEntityId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): MyEntityId {
        return parseMyEntityId(p.valueAsString)
    }
}
