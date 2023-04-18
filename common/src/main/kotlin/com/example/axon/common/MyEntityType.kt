package com.example.axon.common

import com.example.axon.common.MyEntityId.Companion.myEntityId

enum class MyEntityType {
    OTHER1,
    OTHER2,
    OTHER3;

    val id: MyEntityId
        get() = myEntityId(this)
}

