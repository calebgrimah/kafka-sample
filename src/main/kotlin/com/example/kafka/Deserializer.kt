package com.example.kafka

import Event
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.kafka.common.serialization.Deserializer


class JacksonDeserializer(
    private val objectMapper: ObjectMapper = ObjectMapper()
        .registerModule(JavaTimeModule())
        .registerModule(KotlinModule())
) :
    Deserializer<Event?> {

    override fun deserialize(topic: String?, data: ByteArray?): Event? {
        try {
            if (data == null) {
                return null
            }
            return objectMapper.readValue(data, Event::class.java)
        } catch (e: Exception) {
            throw Exception("Error deserializing JSON message", e)
        }
    }
}
