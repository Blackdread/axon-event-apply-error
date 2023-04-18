package com.example.axon.common

data class RootMyEntityOtherAddCmd(
    override val myEntityType: MyEntityType,
    override val myEntityOtherId: MyEntityOtherId,
    val level: Int,
) : RootMyEntityOtherCmd()
