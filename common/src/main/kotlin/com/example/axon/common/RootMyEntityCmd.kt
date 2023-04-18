package com.example.axon.common

import org.axonframework.modelling.command.TargetAggregateIdentifier

abstract class RootMyEntityCmd {

    @TargetAggregateIdentifier
    val rootId: RootId = RootId.rootId()

    val myEntityId: MyEntityId
        get() = myEntityType.id

    abstract val myEntityType: MyEntityType

}
