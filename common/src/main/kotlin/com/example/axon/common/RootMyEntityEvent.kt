package com.example.axon.common

abstract class RootMyEntityEvent {

    val rootId: RootId = RootId.rootId()

    val myEntityId: MyEntityId
        get() = myEntityType.id

    abstract val myEntityType: MyEntityType

}
