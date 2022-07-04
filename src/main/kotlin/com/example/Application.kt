package com.example

import Event
import com.example.kafka.buildConsumer
import com.example.kafka.buildProducer
import io.ktor.server.application.*
import com.example.plugins.*
import com.example.service.EventService
import io.ktor.util.*
import kotlinx.coroutines.launch

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@OptIn(KtorExperimentalAPI::class)
fun Application.module() {
    val eventProducer = buildProducer<Long, Event>(environment)
    val eventConsumer = buildConsumer<Long, Event>(environment)
    launch {
        eventConsumer.run()
    }
    val eventService = EventService(eventProducer)
    configureRouting(eventService)
    configureSerialization()
}
