package com.example.axon.common

data class RootMyEntityOtherAddedEvent(
    override val myEntityType: MyEntityType,
    override val myEntityOtherId: MyEntityOtherId,
    val level: Int,
) : RootMyEntityOtherEvent()
