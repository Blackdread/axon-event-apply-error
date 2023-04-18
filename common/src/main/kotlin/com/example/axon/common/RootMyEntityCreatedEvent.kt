package com.example.axon.common

data class RootMyEntityCreatedEvent(
    override val myEntityType: MyEntityType,
) : RootMyEntityEvent()

