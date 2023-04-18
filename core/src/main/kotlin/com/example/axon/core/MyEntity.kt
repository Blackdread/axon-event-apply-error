package com.example.axon.core

import com.example.axon.common.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.extensions.kotlin.applyEvent
import org.axonframework.modelling.command.EntityId

class MyEntity(
    private val myEntityId: MyEntityId,
    @EntityId
    private val myEntityType: MyEntityType,
    private val parent: RootAggregate,
) {

    internal val levels = mutableMapOf<MyEntityOtherId, MyEntityOtherLevel>()


    // ========= Add level

    @CommandHandler
    private fun handle(cmd: RootMyEntityOtherAddCmd) {
        if (levels.values.any { it.level == cmd.level }) {
            throw IllegalStateException("level exist")
        }
        applyEvent(
//        AggregateLifecycle.apply(
            RootMyEntityOtherAddedEvent(cmd.myEntityType, cmd.myEntityOtherId, cmd.level)
        )
    }

    @EventSourcingHandler
    private fun on(evt: RootMyEntityOtherAddedEvent) {
        levels[evt.myEntityOtherId] = MyEntityOtherLevel(
            myEntityId = evt.myEntityId,
            myEntityOtherId = evt.myEntityOtherId,
            myEntityType = evt.myEntityType,
            level = evt.level,
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MyEntity) return false

        if (myEntityId != other.myEntityId) return false

        return true
    }

    override fun hashCode(): Int {
        return myEntityId.hashCode()
    }

}

internal class MyEntityOtherLevel(
    val myEntityId: MyEntityId,
    val myEntityOtherId: MyEntityOtherId,
    val myEntityType: MyEntityType,
    val level: Int,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MyEntityOtherLevel) return false

        if (myEntityId != other.myEntityId) return false
        if (myEntityOtherId != other.myEntityOtherId) return false
        if (myEntityType != other.myEntityType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = myEntityId.hashCode()
        result = 31 * result + myEntityOtherId.hashCode()
        result = 31 * result + myEntityType.hashCode()
        return result
    }

}
