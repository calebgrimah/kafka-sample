package com.example.plugins

import Event
import com.example.service.EventService
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import org.apache.kafka.common.security.oauthbearer.secured.HttpAccessTokenRetriever.post

fun Application.configureRouting(eventService: EventService) {

    routing {
        get("/") {
            call.respondText("Check your application console")
        }

        post ("/events"){
            val event = call.receive<Event>()
            eventService.sendEvent(event)
            call.respond(HttpStatusCode.Accepted)
        }
    }
}
