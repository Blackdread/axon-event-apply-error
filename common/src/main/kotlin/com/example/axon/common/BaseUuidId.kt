package com.example.axon.common

abstract class BaseUuidId : UuidId {

//    abstract override val uuid: UUID

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseUuidId) return false

        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }

    override fun toString(): String {
        return uuid.toString()
    }

}
