package com.example.axon.common

import com.example.axon.common.MyEntityOtherId.Companion.parseMyEntityOtherId
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.axonframework.common.IdentifierFactory
import java.util.*

@JsonDeserialize(using = MyEntityOtherIdDeserializer::class)
@JsonSerialize(using = UuidIdSerializer::class)
class MyEntityOtherId private constructor(
    override val uuid: UUID = UUID.fromString(IdentifierFactory.getInstance().generateIdentifier())
) : BaseUuidId(), UuidId {

    private constructor(identifier: String) : this(UUID.fromString(identifier))

    companion object {

        fun myEntityOtherId(): MyEntityOtherId = MyEntityOtherId()

        fun myEntityOtherId(uuid: UUID): MyEntityOtherId = MyEntityOtherId(uuid)

        fun parseMyEntityOtherId(str: String): MyEntityOtherId {
            return MyEntityOtherId(str)
        }
    }

}

class MyEntityOtherIdDeserializer : JsonDeserializer<MyEntityOtherId>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): MyEntityOtherId {
        return parseMyEntityOtherId(p.valueAsString)
    }
}
