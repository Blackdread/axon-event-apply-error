package com.example.axon.core

import com.example.axon.common.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.extensions.kotlin.applyEvent
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.modelling.command.ForwardMatchingInstances
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.util.*

@Suppress("unused")
@Aggregate
class RootAggregate {

    companion object {
        private val log = LoggerFactory.getLogger(RootAggregate::class.java)
    }

    @AggregateIdentifier
    private lateinit var rootId: RootId

    @AggregateMember(eventForwardingMode = ForwardMatchingInstances::class)
    val myEntities = EnumMap<MyEntityType, MyEntity>(MyEntityType::class.java)

    private constructor() {
        // Required by Axon Framework
    }

    @CommandHandler
    private constructor(cmd: RootCreateCmd) {
//        AggregateLifecycle.apply(RootCreatedEvent(cmd.example))
        applyEvent(RootCreatedEvent(cmd.example))
    }

    @EventSourcingHandler
    private fun on(evt: RootCreatedEvent) {
        rootId = evt.rootId
    }

    // ========= Entity

    @CommandHandler
    private fun handle(cmd: RootMyEntityCreateCmd) {
        if (myEntities.containsKey(cmd.myEntityType)) {
            throw IllegalStateException("bla")
        }
        applyEvent(
//        AggregateLifecycle.apply(
            RootMyEntityCreatedEvent(
                cmd.myEntityType,
            )
        )
    }

    @EventSourcingHandler
    private fun on(evt: RootMyEntityCreatedEvent) {
        myEntities[evt.myEntityType] = MyEntity(evt.myEntityId, evt.myEntityType, this)
    }


}

