package com.example.axon.common

data class RootCreatedEvent(
    val example: String,
) {

    val rootId: RootId = RootId.rootId()

}
