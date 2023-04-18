package com.example.axon.web

import com.example.axon.common.*
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.time.ZoneOffset.UTC
import java.util.*

@SpringBootApplication(
    exclude = [DataSourceAutoConfiguration::class]
)
class WebApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone(UTC))
    runApplication<WebApplication>(*args)
}

@RestController
class MyController(
    private val reactorCommandGateway: ReactorCommandGateway,
) {

    val myEntityOtherId = MyEntityOtherId.myEntityOtherId()

    @GetMapping("/api/create-root")
    fun createRoot(): Mono<RootId> {
        return reactorCommandGateway.send(RootCreateCmd("a"))
    }

    @GetMapping("/api/create-my-entity")
    fun createRootMyEntity(): Mono<Unit> {
        return reactorCommandGateway.send(RootMyEntityCreateCmd(MyEntityType.OTHER1))
    }

    @GetMapping("/api/create-my-entity-level/{level}")
    fun addRootMyEntityOther(@PathVariable level: Int): Mono<Unit> {
        return reactorCommandGateway.send(RootMyEntityOtherAddCmd(MyEntityType.OTHER1, myEntityOtherId, level))
    }

}