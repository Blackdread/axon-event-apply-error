package com.example.axon.core

import com.example.axon.context.ContextMarker
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.ZoneOffset.UTC
import java.util.*

@SpringBootApplication(
    scanBasePackageClasses = [
        ContextMarker::class,
        CoreApplication::class,
    ]
)
class CoreApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone(UTC))
    runApplication<CoreApplication>(*args)
}
