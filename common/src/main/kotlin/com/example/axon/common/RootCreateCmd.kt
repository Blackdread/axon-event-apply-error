package com.example.axon.common

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RootCreateCmd(
    val example: String,
) {

    @TargetAggregateIdentifier
    val rootId: RootId = RootId.rootId()

}