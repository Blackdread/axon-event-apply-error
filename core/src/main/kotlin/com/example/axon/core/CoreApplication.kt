package com.example.axon.core

import com.example.axon.context.ContextMarker
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackageClasses = [
        ContextMarker::class,
        CoreApplication::class,
    ]
)
class CoreApplication

fun main(args: Array<String>) {
    runApplication<CoreApplication>(*args)
}
