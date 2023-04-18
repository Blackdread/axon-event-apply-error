package com.example.axon.common

data class RootMyEntityCreateCmd(
    override val myEntityType: MyEntityType,
) : RootMyEntityCmd()

